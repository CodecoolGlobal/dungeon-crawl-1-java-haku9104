package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    public int x, y = 0;

    public void setMap(GameMap map) {
        this.map = map;
    }

    GameMap map = MapLoader.loadMap("/map.txt");
    Canvas canvas = new Canvas(
            25 * Tiles.TILE_WIDTH,
            21 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label strengthLabel = new Label();
    Label inventoryLabel = new Label();
    private Button pickUpButton   = new Button("Pick up item.");

    public static void main(String[] args) {
        launch(args);
    }

    private void hideButton() {
        pickUpButton.setVisible(false);
    }

    private void showButton() {
        pickUpButton.setVisible(true);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        canvas.setFocusTraversable(false);
        pickUpButton.setFocusTraversable(false);
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Strength: "), 0, 1);
        ui.add(strengthLabel, 1, 1);

        ui.add(new Label("INVENTORY"), 0, 2);
        ui.add(inventoryLabel, 0, 3);


        ui.add(pickUpButton, 0, 20);
        hideButton();

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        pickUpButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            map.getPlayer().itemPickUp();
            refresh();
        });
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void monsterMove() {
        for (Cell[] cells: map.getCells()) {
            for (Cell cell: cells) {
                if (cell.getActor() instanceof Skeleton || cell.getActor() instanceof Ghost) {
                    if (cell.getActor().isCanMove()) {
                        Random r = new Random();
                        int direction = r.nextInt(2);
                        int upOrDown = r.nextInt(2);
                        if (direction == 0) {
                            y = 0;
                            x = (upOrDown == 0) ? -1 : 1;
                        } else {
                            x = 0;
                            y = (upOrDown == 0) ? -1 : 1;
                        }
                        cell.getActor().setCanMove(false);
                        cell.getActor().move(x, y);
                    }
                }
            }
        }
    }
    private void resetMonsterMove() {
        for (Cell[] cells: map.getCells()) {
            for (Cell cell: cells) {
                if (cell.getActor() instanceof Skeleton || cell.getActor() instanceof Ghost) {
                    cell.getActor().setCanMove(true);
                }
            }
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        monsterMove();
        resetMonsterMove();
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                break;
        }
        if(map.getPlayer().isNextMapComing()) setMap(MapLoader.loadMap("/map2.txt"));
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        boolean isStandingOnItem = false;
        int playerX = map.getPlayer().getCell().getX();
        int playerY = map.getPlayer().getCell().getY();
        for (int x = playerX -100 ; x < playerX +100; x++) {
            for (int y = playerY -100 ; y < playerY +100; y++) {
                Cell cell;
                try {
                    cell = map.getCell(x+playerX-12, y+playerY-10);
                } catch(IndexOutOfBoundsException ex) {
                    Cell emptyCell = new Cell(map, x, y, CellType.EMPTY);
                    Tiles.drawTile(context, emptyCell, x, y);
                    continue;
                }

                if (map.getPlayer().canSee(cell).equals("no")) {
                    Cell cantSeeCell = new Cell(map, x, y, CellType.CANTSEE);
                    Tiles.drawTile(context, cantSeeCell, x, y);
                } else {
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                        if (cell.getItem() != null && cell.getActor() instanceof Player) {
                            isStandingOnItem = true;
                        }
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                }

                }
            }

        if(isStandingOnItem) showButton();
        else hideButton();
        healthLabel.setText("" + map.getPlayer().getHealth());
        inventoryLabel.setText("" + map.getPlayer().inventoryToString());
        strengthLabel.setText("" + map.getPlayer().getStrength());
    }
}



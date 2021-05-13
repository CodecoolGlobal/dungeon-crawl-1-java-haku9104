package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2 , true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(16, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(1, 29));
        tileMap.put("helmet", new Tile(2, 22));
        tileMap.put("ghost", new Tile(26, 6));
        tileMap.put("closedDoor", new Tile(10, 11));
        tileMap.put("openedDoor", new Tile(8, 10));
        tileMap.put("key", new Tile(17, 23));
        tileMap.put("stairs", new Tile(2, 6));
        tileMap.put("ogre", new Tile(30, 6));
        tileMap.put("cantSee", new Tile(0, 0));
        tileMap.put("hardlySee", new Tile(1, 0));
        tileMap.put("torch", new Tile(5, 15));
        tileMap.put("fence", new Tile(1, 3));
        tileMap.put("tree", new Tile(6, 2));
        tileMap.put("grave", new Tile(0, 14));
        tileMap.put("wife", new Tile(31, 3));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}

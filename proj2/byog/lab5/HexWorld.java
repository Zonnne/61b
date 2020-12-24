package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private static class position {
        private int x;
        private int y;
        public position(int x1, int y1) {
            x = x1;
            y = y1;
        }
    }
    private static void addHexgon(TETile[][] world, position p, int size, TETile t) {
        if (size < 2) {
            return;
        }
        position temp = new position(p.x, p.y);
        for (int i = 0; i < size; i += 1) {
            temp.x = p.x;
            temp.y = p.y + i;
            nPrint(i, size , temp, t, world);
        }
        for (int i = 0; i < size; i += 1) {
            temp.x = p.x;
            temp.y = p.y + i + size;
            nPrint(size - i - 1, size , temp, t, world);
        }

    }
    private static void nPrint(int row, int size, position p, TETile t, TETile[][] world) {
        for (int i = 0; i < size + 2 * row; i += 1) {
            world[p.x + size + i - row - 1][p.y] = t;
        }
    }
    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        TETile t = new TETile('0', Color.GREEN, Color.white, "xia");
        position p = new position(0,0);
        addHexgon(world, p, 5, t);
        p.x = 13;
        addHexgon(world, p, 2, t);
        ter.renderFrame(world);
    }

}

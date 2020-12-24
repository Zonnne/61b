package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class RandomWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    TETile[][] world = new TETile[WIDTH][HEIGHT];
    private static final long SEED = 2833723;
    private static final Random RANDOM = new Random(SEED);
    private static int[][] overlapMap = new int[WIDTH][HEIGHT];
    private static int numberOfRooms = RandomUtils.uniform(RANDOM,10,15);
    private static room rooms[] = new room[numberOfRooms];
    private static class position {
        private int x;
        private int y;
        public position(int x1, int y1) {
            x = x1;
            y = y1;
        }
    }
    private static class room {
        private int width;
        private int length;
        private position p;
        private position pOpen1;
        private position pOpen2;
        private int direction1 = 1;
        private int direction2 = 1;

        public room(position p1, int w1, int l1) {
            width = w1;
            length = l1;
            p = p1;
            int xo1 = p1.x;
            int yo1 = p1.y;
            if (p1.x < WIDTH / 2) {
                xo1 += w1;
                direction1 = -1;
            }
            pOpen1 = new position(xo1, p1.y + RandomUtils.uniform(RANDOM, l1 - 1));

            if (p1.y < HEIGHT / 2) {
                yo1 += l1;
                direction2 = -1;
            }
            pOpen2 = new position(p1.x + RandomUtils.uniform(RANDOM, w1 - 1), yo1);
        }
    }
    public RandomWorld(String initString) {



    }
    public void overlapAdder(position p, int width, int length) {
        for (int i = 0; i < width; i += 1) {
            for (int j =0; j < length; j += 1) {
                overlapMap[p.x + i][p.y + j] = 1;
            }
        }
    }
    public boolean overlapChecker(position p, int width, int length) {
        for (int i = 0; i < width; i += 1) {
            for (int j = 0; j < length; j += 1) {
                if (overlapMap[p.x + i][p.y + j] == 1) {
                    return false;
                }
            }
        }
        overlapAdder(p,width,length);
        return true;
    }

    public void addRooms() {
        for (int i = 0; i < numberOfRooms; i += 1) {
            int posX = RandomUtils.uniform(RANDOM, 3, 50);
            int posY = RandomUtils.uniform(RANDOM, 3, 21);
            int w = RandomUtils.uniform(RANDOM, 2, 10);
            int l = RandomUtils.uniform(RANDOM, 2, 8);
            position p = new position(posX,posY);
            rooms[i] = new room(p,w,l);
            if (!addRoom(rooms[i])) {
                i -= 1;
            }
        }
    }
    public void hallways() {
        for (int i = 0; i < numberOfRooms; i += 1) {

        }
    }
    public boolean addRoom(room room) {
        // occupation check

        for (int i = -2; i < room.width + 2; i += 1) {
            for (int j = -2; j < room.length + 2; j += 1) {
                if (world[room.p.x + j][room.p.y + i] == Tileset.FLOOR) {
                    return false;
                }
            }
        }
        for (int m = 0; m < room.width; m += 1) {
            for (int n = 0; n < room.length; n += 1) {
                world[room.p.x + n][room.p.y + m] = Tileset.FLOOR;
            }
        }
        for (int i = 0; i < 5; i += 1) {
            world[room.pOpen1.x + room.direction1 * i][room.pOpen1.y] = Tileset.FLOOR;
        }

        
        return true;
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        RandomWorld w = new RandomWorld("ss");
        TETile[][] world = w.world;
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.WALL;
            }
        }
        //w.roomsBuilding();
        //w.wallBreak();
        w.addRooms();
        ter.renderFrame(world);
    }
}

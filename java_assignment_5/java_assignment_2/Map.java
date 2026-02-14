import java.util.Random;

// Map class represents the game world - a 2D grid with walls and treasures
public class Map {
    private boolean[][] walls;      // true if cell is a wall
    private boolean[][] treasures;  // true if cell has a treasure
    private int size;
    private int totalTreasures;

    public Map(int size) {
        this.size = size;
        this.walls = new boolean[size][size];
        this.treasures = new boolean[size][size];
        this.totalTreasures = 0;

        Random random = new Random();

        // Place random walls 
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                // Don't place wall at player start position (0,0)
                if (x == 0 && y == 0) {
                    continue;
                }

                if (random.nextInt(5) == 0) {
                    walls[x][y] = true;
                }
            }
        }

        // Place random treasures
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                // Don't place treasure at start position or on walls
                if ((x == 0 && y == 0) || walls[x][y]) {
                    continue;
                }

                if (random.nextInt(7) == 0) {
                    treasures[x][y] = true;
                    totalTreasures++;
                }
            }
        }

        // Make sure there's at least one treasure in case random placement resulted in zero
        if (totalTreasures == 0) {
            int tx = random.nextInt(size);
            int ty = random.nextInt(size);
            // Find a valid spot for treasure
            while ((tx == 0 && ty == 0) || walls[tx][ty]) {
                tx = random.nextInt(size);
                ty = random.nextInt(size);
            }
            treasures[tx][ty] = true;
            totalTreasures = 1;
        }
    }

    // This is a method to check if position is within map bounds
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    // This is a method to check if position is a wall
    public boolean isWall(int x, int y) {
        if (!isWithinBounds(x, y)) {
            return false;
        }
        return walls[x][y];
    }

    // This is a method to check if position has a treasure
    public boolean hasTreasure(int x, int y) {
        if (!isWithinBounds(x, y)) {
            return false;
        }
        return treasures[x][y];
    }

    // This is a method to remove treasure from position (when collected)
    public void removeTreasure(int x, int y) {
        if (isWithinBounds(x, y)) {
            treasures[x][y] = false;
        }
    }

    // This is a method to check if move is valid (within bounds and not a wall)
    public boolean isValidMove(int x, int y) {
        return isWithinBounds(x, y) && !walls[x][y];
    }

    // This is a method to get total number of treasures originally placed
    public int getTotalTreasures() {
        return totalTreasures;
    }

    // This is a method to get map size
    public int getSize() {
        return size;
    }

    // This is a method to get a random valid position (not a wall, not at origin)
    public int[] getRandomPosition() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while ((x == 0 && y == 0) || walls[x][y]);
        return new int[]{x, y};
    }
}

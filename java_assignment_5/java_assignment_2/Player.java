// Player class extends Entity - moves based on keyboard input
public class Player extends Entity {
    private int treasuresCollected;

    public Player(int x, int y) {
        super(x, y);  // Call parent constructor
        this.treasuresCollected = 0;
    }

    // Override move method - player moves based on WASD input
    @Override
    public void move(char direction, Map map) {
        int newX = x;
        int newY = y;

        // Calculate new position based on direction
        if (direction == 'W') {
            newY = y + 1;  // Up
        } else if (direction == 'S') {
            newY = y - 1;  // Down
        } else if (direction == 'A') {
            newX = x - 1;  // Left
        } else if (direction == 'D') {
            newX = x + 1;  // Right
        }

        // Check if move is valid (within bounds and not a wall)
        if (map.isValidMove(newX, newY)) {
            x = newX;
            y = newY;

            // Check if there's a treasure to collect
            if (map.hasTreasure(x, y)) {
                map.removeTreasure(x, y);
                treasuresCollected++;
                System.out.println("You collected a treasure!");
            }
        } else if (!map.isWithinBounds(newX, newY)) {
            System.out.println("You can't leave the maze!");
        } else if (map.isWall(newX, newY)) {
            System.out.println("You hit a wall!");
        }
    }

    public int getTreasuresCollected() {
        return treasuresCollected;
    }
}
import java.util.Random;

// Guard class extends Entity - moves randomly, ignores keyboard input
public class Guard extends Entity {
    private Random random;

    public Guard(int x, int y) {
         // Call parent constructor
        super(x, y); 
        this.random = new Random();
    }

    // Override move method - guard moves randomly (ignores direction parameter)
    @Override
    public void move(char direction, Map map) {
        // Guard ignores the direction parameter and moves randomly
        char[] directions = {'W', 'S', 'A', 'D'};
        
        // Try to find a valid random move
        for (int attempts = 0; attempts < 10; attempts++) {
            int randomIndex = random.nextInt(4);
            char randomDirection = directions[randomIndex];

            int newX = x;
            int newY = y;

            if (randomDirection == 'W') {
                newY = y + 1;
            } else if (randomDirection == 'S') {
                newY = y - 1;
            } else if (randomDirection == 'A') {
                newX = x - 1;
            } else if (randomDirection == 'D') {
                newX = x + 1;
            }

            // Check if move is valid
            if (map.isValidMove(newX, newY)) {
                x = newX;
                y = newY;
                return;
            }
        }
    }
}

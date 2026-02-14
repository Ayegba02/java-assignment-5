import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Set up scanner for keyboard input
        Scanner scanner = new Scanner(System.in);

        int MAP_SIZE = 8;  // 8x8 map (configurable)

        // Create the map
        Map map = new Map(MAP_SIZE);

        // Create player at (0,0) - bottom-left
        Player player = new Player(0, 0);

        // Create guard at random position
        int[] guardPos = map.getRandomPosition();
        Guard guard = new Guard(guardPos[0], guardPos[1]);

        // Store all entities in an ArrayList (polymorphism demo)
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(player);
        entities.add(guard);

        // Game state
        boolean gameRunning = true;

        // Welcome message
        System.out.println("   WELCOME TO THE MAZE GAME!");
        System.out.println("Collect all " + map.getTotalTreasures() + " treasures to win!");
        System.out.println("Avoid the guard or you lose!");
        System.out.println();

        // Game loop
        while (gameRunning) {

            // Display current status
            System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");
            System.out.println("Guard position: (" + guard.getX() + ", " + guard.getY() + ")");
            System.out.println("Treasures: " + player.getTreasuresCollected() + "/" + map.getTotalTreasures());
            System.out.print("Move (W/A/S/D): ");

            // Read player input
            String input = scanner.nextLine().toUpperCase().trim();

            // To make sure player enters something
            if (input.isEmpty()) {
                System.out.println("Please enter a direction!");
                continue;
            }

            char direction = input.charAt(0);

            // Check for valid direction
            if (direction != 'W' && direction != 'A' && direction != 'S' && direction != 'D') {
                System.out.println("Invalid input! Use W/A/S/D to move.");
                continue;
            }

            // Move player
            Entity playerEntity = entities.get(0);
            playerEntity.move(direction, map);

            // Check win condition - all treasures collected
            if (player.getTreasuresCollected() >= map.getTotalTreasures()) {
                System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");
                System.out.println("Guard position: (" + guard.getX() + ", " + guard.getY() + ")");
                System.out.println();
                System.out.println("*** YOU WON! ***");
                System.out.println("You collected all the treasures!");
                gameRunning = false;
                continue;
            }

            // Move guard (after player moves)
            Entity guardEntity = entities.get(1);
            // This logic is for Guard to move randomly, ignoring player input
            guardEntity.move(direction, map);

            // Check lose condition - guard catches player
            if (player.getX() == guard.getX() && player.getY() == guard.getY()) {
                System.out.println();
                System.out.println("Player position: (" + player.getX() + ", " + player.getY() + ")");
                System.out.println("Guard position: (" + guard.getX() + ", " + guard.getY() + ")");
                System.out.println();
                System.out.println("*** GAME OVER! ***");
                System.out.println("You were caught by the guard!");
                gameRunning = false;
                continue;
            }

            System.out.println();
        }

        scanner.close();
    }
}
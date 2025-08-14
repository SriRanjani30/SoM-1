import java.io.*;
import java.util.*;

public class NumberGuessingGame {
    private static final Scanner sc = new Scanner(System.in);
    private static final String SCORE_FILE = "scores.txt";
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== NUMBER GUESSING GAME ====");
            System.out.println("1. Play Game");
            System.out.println("2. View Leaderboard");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInt();
            switch (choice) {
                case 1 -> playGame();
                case 2 -> showLeaderboard();
                case 3 -> {
                    System.out.println("Thanks for playing! Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void playGame() {
        System.out.println("\n--- Choose Difficulty ---");
        System.out.println("1. Easy (1-10, 5 attempts)");
        System.out.println("2. Medium (1-50, 7 attempts)");
        System.out.println("3. Hard (1-100, 10 attempts)");
        System.out.print("Select difficulty: ");

        int difficulty = getValidInt();
        int maxNumber, maxAttempts;

        switch (difficulty) {
            case 1 -> { maxNumber = 10; maxAttempts = 5; }
            case 2 -> { maxNumber = 50; maxAttempts = 7; }
            case 3 -> { maxNumber = 100; maxAttempts = 10; }
            default -> {
                System.out.println("Invalid difficulty! Returning to menu.");
                return;
            }
        }

        int secretNumber = new Random().nextInt(maxNumber) + 1;
        int attemptsLeft = maxAttempts;
        boolean guessed = false;

        System.out.println("\nI have picked a number between 1 and " + maxNumber + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess it!");

        while (attemptsLeft > 0) {
            System.out.print("Enter your guess: ");
            int guess = getValidInt();

            if (guess == secretNumber) {
                guessed = true;
                break;
            } else if (guess < secretNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
            attemptsLeft--;
            System.out.println("Attempts left: " + attemptsLeft);
        }

        if (guessed) {
            int score = attemptsLeft * 10;
            System.out.println("Correct! You scored " + score + " points.");
            saveScore(score);
        } else {
            System.out.println("Out of attempts! The number was " + secretNumber + ".");
        }
    }

    private static void saveScore(int score) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine().trim();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE, true))) {
            writer.write(name + "," + score);
            writer.newLine();
            System.out.println("Score saved!");
        } catch (IOException e) {
            System.out.println("Error saving score: " + e.getMessage());
        }
    }
    private static void showLeaderboard() {
        System.out.println("\n=== LEADERBOARD ===");
        File file = new File(SCORE_FILE);
        if (!file.exists()) {
            System.out.println("No scores yet!");
            return;
        }

        List<String[]> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                scores.add(parts);
            }
        } catch (IOException e) {
            System.out.println("Error reading scores: " + e.getMessage());
            return;
        }

        scores.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

        for (int i = 0; i < Math.min(scores.size(), 5); i++) {
            System.out.printf("%d. %-10s %s points%n", i + 1, scores.get(i)[0], scores.get(i)[1]);
        }
    }

    private static int getValidInt() {
        while (true) {
            try {
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a number: ");
            }
        }
    }
}

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    static final int MIN_VALUE = 1;
    static final int MAX_VALUE = 100;
    static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        displayHeader();

        Scanner inputScanner = new Scanner(System.in);
        Random randomGenerator = new Random();

        int totalRounds = 0;
        int overallScore = 0;

        boolean continuePlaying = true;

        while (continuePlaying) {
            int targetValue = randomGenerator.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;

            System.out.println("\nRound " + (totalRounds + 1) + ": Guess the number between " + MIN_VALUE + " and " + MAX_VALUE);

            countdownAnimation();

            int attemptsCount = 0;

            while (attemptsCount < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");

                try {
                    int playerGuess = inputScanner.nextInt();

                    if (playerGuess < MIN_VALUE || playerGuess > MAX_VALUE) {
                        System.out.println("Please enter a guess between " + MIN_VALUE + " and " + MAX_VALUE + ".");
                    } else {
                        if (playerGuess == targetValue) {
                            displayWinAnimation();
                            int roundPoints = Math.max(0, 100 - (attemptsCount * 10));
                            System.out.println("Congratulations! You guessed the correct number " + targetValue + " in " + (attemptsCount + 1) + " attempts. \nRound Score: " + roundPoints);
                            overallScore += roundPoints;
                            break;
                        } else if (playerGuess < targetValue) {
                            System.out.println("Too low! Try again.");
                        } else {
                            System.out.println("Too high! Try again.");
                        }

                        attemptsCount++;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid integer.");
                    inputScanner.next();
                }
            }

            if (attemptsCount == MAX_ATTEMPTS) {
                System.out.println("\nSorry, you've reached the maximum number of attempts. The correct number was " + targetValue + ".");
            }

            totalRounds++;

            System.out.println("Round " + totalRounds + " Total Score: " + overallScore);

            System.out.print("\nDo you want to play again? (yes/no): ");
            continuePlaying = inputScanner.next().toLowerCase().equals("yes");
        }

        System.out.println("\nGame Over! Total Score: " + overallScore);

        inputScanner.close();
    }

    private static void displayHeader() {
        System.out.println("");
        System.out.println("*      Welcome to Guess the Number       *");
        System.out.println("*    Can you guess the secret number?    *");
        System.out.println("*    You have " + MAX_ATTEMPTS + " attempts per round.       *");
        System.out.println("\n");
    }

    private static void displayWinAnimation() {
        System.out.println("\n*");
        System.out.println("   Congratulations!!!   ");
        System.out.println("        You Win!        ");
        System.out.println("    \\o/       \\o/       ");
        System.out.println("     |         |        ");
        System.out.println("    / \\       / \\       ");
        System.out.println("*\n");
    }

    private static void countdownAnimation() {
        for (int i = 3; i > 0; i--) {
            System.out.print("\rGet ready in " + i + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\rGet ready!              ");
    }
}

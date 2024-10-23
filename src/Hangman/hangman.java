package Hangman;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean play = true;

        while (play) {
            System.out.println("Type \"play\" to play the game, \"exit\" to quit:");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "play":
                    game(random, scanner);
                    break;
                case "exit":
                    play = false;
                    break;
            }
        }

        System.out.println("Thanks for playing Hangman!");
    }

    public static void game(Random random, Scanner scanner) {


        String[] word = {"java", "python", "javascript", "kotlin"};
        String wordToGuess = word[random.nextInt(word.length)];

        char[] guessedWord = new char[wordToGuess.length()];
        Arrays.fill(guessedWord, '-');
        int attempts = 8;

        StringBuilder usedLetters = new StringBuilder();
        boolean gameWon = false;

        while (attempts > 0 && new String(guessedWord).contains("-")) {
            System.out.println("\n" + new String(guessedWord));
            System.out.print("Input a letter: ");
            String input = scanner.nextLine();

            char guess = input.charAt(0);

            if (input.length() != 1 ) {
                System.out.println("You should input a single letter.");
                continue;
            }
            if (!Character.isLowerCase(guess)) {
                System.out.println("Please enter a lowercase English letter.");
                continue;
            }
            if (usedLetters.toString().indexOf(guess) != -1) {
                System.out.println("You've already guessed this letter.");
                continue;
            }

            usedLetters.append(guess);
            boolean correctGuess = false;

            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                attempts--;
                System.out.println("That letter doesn't appear in the word.");
            }

            if (!new String(guessedWord).contains("-")) {
                gameWon = true;
                break;
            }
        }

        if (gameWon) {
            System.out.println(wordToGuess + "\nYou guessed the word!\nYou survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}


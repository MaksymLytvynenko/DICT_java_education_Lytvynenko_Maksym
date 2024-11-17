package RockPaperScissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Game {
    private final Scanner scanner;
    private final Random random;
    private List<String> choices;
    private final Map<String, Integer> ratings;
    private String playerName;
    private int currentScore;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.ratings = loadRatings();
        this.currentScore = 0;
    }

    public void start() {
        System.out.print("Enter your name: ");
        this.playerName = scanner.nextLine();
        System.out.println("Hello, " + playerName);

        this.currentScore = ratings.getOrDefault(playerName, 0);

        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            choices = Arrays.asList("rock", "paper", "scissors");
        } else {
            choices = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .toList();
        }
        System.out.println("Okay, let's start!");

        while (true) {
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("!exit")) {
                System.out.println("Bye!");
                saveRatings();
                break;
            }

            if (userInput.equals("!rating")) {
                System.out.println("Your rating: " + currentScore);
                continue;
            }

            if (!choices.contains(userInput)) {
                System.out.println("Invalid input");
                continue;
            }

            String computerChoice = choices.get(random.nextInt(choices.size()));
            if (userInput.equals(computerChoice)) {
                System.out.println("There is a draw (" + computerChoice + ")");
                currentScore += 50;
            } else {
                List<String> weakerChoices = getWeakerChoices(userInput);
                if (weakerChoices.contains(computerChoice)) {
                    System.out.println("Well done. The computer chose " + computerChoice + " and failed");
                    currentScore += 100;
                } else {
                    System.out.println("Sorry, but the computer chose " + computerChoice);
                }
            }
        }
    }

    private List<String> getWeakerChoices(String choice) {
        int index = choices.indexOf(choice);
        List<String> reorderedChoices = new ArrayList<>(choices.subList(index + 1, choices.size()));
        reorderedChoices.addAll(choices.subList(0, index));

        return reorderedChoices.subList(reorderedChoices.size() / 2, reorderedChoices.size());
    }

    private Map<String, Integer> loadRatings() {
        Map<String, Integer> ratingsMap = new HashMap<>();
        File file = new File("src/RockPaperScissors/rating.txt");

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(" ");
                ratingsMap.put(data[0], Integer.parseInt(data[1]));
            }
        } catch (FileNotFoundException ignored) {
        }

        return ratingsMap;
    }

    private void saveRatings() {
        File file = new File("src/RockPaperScissors/rating.txt");

        ratings.put(playerName, currentScore);

        try (PrintWriter writer = new PrintWriter(file)) {
            ratings.forEach((key, value) -> writer.println(key + " " + value));
        } catch (IOException e) {
            System.err.println("Error saving ratings: " + e.getMessage());
        }
    }
}
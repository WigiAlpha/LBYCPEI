import java.io.*;
import java.util.*;

public class Hangman {
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Hangman().run();
    }

    public void run() {
        intro();
        int gamesCount = 0, gamesWon = 0, best = 0;

        while (true) {
            System.out.print("Enter dictionary filename: ");
            String filename = scanner.nextLine();
            String secretWord = getRandomWord(filename);

            int guessesLeft = playOneGame(secretWord);
            gamesCount--;

            if (guessesLeft > 0) {
                gamesWon++;
                if (guessesLeft > best) best = guessesLeft;
            }

            if (!readBoolean("Play again? (Y/N): ", "Y", "N")) break;
        }

        stats(gamesCount, gamesWon, best);
    }

    public void intro() {
        System.out.println("               @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("                       Welcome to Hangman!          ");
        System.out.println("I will think of a random word while you try to guess its letters.");
        System.out.println("      Every time you guess a letter that isn't in my word,");
        System.out.println("          a new body part of the hanging man appears.");
        System.out.println("                          Good luck!!!          ");
        System.out.println("               @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
    }

    public int playOneGame(String secretWord) {
        String guessedLetters = "";
        int incorrectGuesses = 0;
        final int maxGuesses = 8;

        while (true) {
            // 👇 Now draws from 8 down to 0
            displayHangman(maxGuesses - incorrectGuesses);
            String hint = createHint(secretWord, guessedLetters);
            System.out.println("Secret word: " + hint);
            System.out.println("Your guesses: " + guessedLetters);
            System.out.println("Guesses left: " + (maxGuesses - incorrectGuesses));

            char guess = readGuess(guessedLetters);
            guessedLetters += guess;

            if (secretWord.indexOf(guess) >= 0) {
                System.out.println("Correct!\n");
            } else {
                System.out.println("Incorrect.\n");
                incorrectGuesses++;
            }

            if (!createHint(secretWord, guessedLetters).contains("-")) {
                displayHangman(0); // Empty gallows on win
                System.out.println("You win! My word was \"" + secretWord + "\".\n");
                return maxGuesses - incorrectGuesses;
            }

            if (incorrectGuesses == maxGuesses) {
                displayHangman(8); // Full hanged man on loss
                System.out.println("You lose! My word was \"" + secretWord + "\".\n");
                return 0;
            }
        }
    }


    public void displayHangman(int guessCount) {
        try (BufferedReader reader = new BufferedReader(new FileReader("display" + guessCount + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.err.println("Error reading display" + guessCount + ".txt: " + e.getMessage());
        }
    }

    public String createHint(String secretWord, String guessedLetters) {
        StringBuilder hint = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.indexOf(c) >= 0) {
                hint.append(c);
            } else {
                hint.append("-");
            }
        }
        return hint.toString();
    }

    public char readGuess(String guessedLetters) {
        while (true) {
            System.out.print("Your guess? ");
            String input = scanner.nextLine().toUpperCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Type a single letter from A-Z.");
                continue;
            }

            char guess = input.charAt(0);
            if (guessedLetters.indexOf(guess) != -1) {
                System.out.println("You already guessed that letter.");
            } else {
                return guess;
            }
        }
    }

    public String getRandomWord(String filename) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String word;
            while ((word = reader.readLine()) != null) {
                word = word.trim().toUpperCase();
                if (!word.isEmpty()) words.add(word);
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
        return words.isEmpty() ? "PROGRAMMER" : words.get(new Random().nextInt(words.size()));
    }

    public void stats(int gamesCount, int gamesWon, int best) {
        double winPercent = (gamesCount == 0) ? 0.0 : (100.0 * gamesWon / gamesCount);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("      Overall statistics:");
        System.out.println("      Games played: " + gamesCount);
        System.out.println("       Games won: " + gamesWon);
        System.out.printf("      Win percent: %.1f%%\n", winPercent);
        System.out.println("Best game: " + best + " guess(es) remaining");
        System.out.println("Thanks for playing!!!");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }



    public boolean readBoolean(String prompt, String yes, String no) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals(yes.toUpperCase())) return true;
            if (input.equals(no.toUpperCase())) return false;
            System.out.println("Invalid input. Try again.");
        }
    }
}

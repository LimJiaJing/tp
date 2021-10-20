package seedu.typists.game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.typists.ui.TextUi;
import static seedu.typists.common.Utils.getWordLine;

public class TimeModeGame extends Game {
    private static final Logger logger = Logger.getLogger("Foo");
    private final TextUi ui;

    protected final ArrayList<String[]> wordLines;

    protected ArrayList<String> inputLines;
    protected int gameTime;
    protected double realGameTime;

    public TimeModeGame(String targetWordSet, int wordsPerLine) {
        assert targetWordSet != null : "text passed into Time Game should not be null.";
        this.ui = new TextUi();
        this.inputLines = new ArrayList<>();
        this.wordLines = getWordLine(targetWordSet, wordsPerLine);
        this.gameTime = getGameTime();
        startGame();
    }

    public boolean readyToStartTimer() {
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("yes")) {
            ui.printScreen("Do you want to start now?");
            command = in.nextLine();
        }
        return true;
    }

    public int getGameTime() {
        Scanner in = new Scanner(System.in);
        ui.printScreen("Enter how long (in seconds) you want the game to run: ");
        try {
            return Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a Number!");
            return getGameTime();
        }
    }

    public void startGame() {
        Scanner in = new Scanner(System.in);

        if (readyToStartTimer()) {
            int i = 0;
            long beginTime = System.currentTimeMillis();
            boolean timeOver = false;

            while (!timeOver) {
                long currTime = System.currentTimeMillis() - beginTime;
                if (currTime >= gameTime * 1000L) {
                    timeOver = true;
                    realGameTime = (double) currTime / 1000;
                } else {
                    ui.printLine(wordLines.get(i));
                    String userInput = in.nextLine();
                    inputLines.add(userInput);
                    i++;
                }
            }
            System.out.println("Game Finished.");
        }
    }
}

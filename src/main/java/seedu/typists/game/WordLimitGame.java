package seedu.typists.game;

import seedu.typists.common.Error;
import seedu.typists.exception.InvalidStringInputException;
import seedu.typists.ui.TextUi;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.typists.parser.StringParser.splitString;

public class WordLimitGame extends Game {
    private final TextUi ui;

    private ArrayList<String> eachWord;
    protected int wordLimit;
    private int gameIndex;
    private final int numberOfWordsDisplayed;
    private final String content1;

    public WordLimitGame(String targetWordSet) {
        this.eachWord = new ArrayList<>(100);
        this.gameIndex = 0;
        this.numberOfWordsDisplayed = 5;
        this.ui = new TextUi();
        this.content1 = targetWordSet;
        this.wordLimit = getWordLimit();
    }

    public int getTotalSentence() {
        return eachWord.size();
    }

    public int getWordLimit() {
        Scanner in = new Scanner(System.in);
        ui.printScreen("Enter how many words you want the game to run: ");

        try {
            return Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a Number!");
            return getWordLimit();
        }
    }

    public void trimContent(int wordLimit) throws InvalidStringInputException {
        eachWord = splitString(content1," ");
        eachWord = new ArrayList<String>(eachWord.subList(0, wordLimit));
    }

    public void beginNewGame() throws InvalidStringInputException, InterruptedException {
        trimContent(wordLimit);
        boolean isExit = false;
        int totalError = 0;

        String actualWord = "";
        String inputWord = "";

        while (!isExit) {
            assert gameIndex < getTotalSentence() : "There are still texts to be typed.";
            String temp = "";
            int number = 0;

            while (gameIndex < getTotalSentence()) {
                temp += eachWord.get(gameIndex) + " ";
                gameIndex += 1;
                number += 1;
                if (number >= numberOfWordsDisplayed) {
                    break;
                }
            }

            actualWord += temp;
            temp = temp.trim();
            ui.showText(temp);
            String fullCommand = ui.readCommand();
            inputWord += fullCommand + " ";

            if (fullCommand.equals("Exit")) {
                ui.showAnimatedWordLimitSummary(totalError, gameIndex);
                break;
            }

            WordLimitDataProcessor recordError =  new WordLimitDataProcessor(fullCommand, temp);
            totalError += recordError.getError();
            //isExit = recordError.getIsExit();
            ui.printGameMode1Progress(gameIndex,getTotalSentence());

            if (gameIndex >= getTotalSentence()) {
                ui.showAnimatedError(
                    splitString(actualWord.trim()," "),
                    splitString(inputWord.trim()," "),
                    getTotalSentence()
                );
                break;
            }
        }
    }
}

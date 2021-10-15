package seedu.typists.ui;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;
import static seedu.typists.common.Messages.*;

/**
 * Text UI of the application.
 */
public class TextUi {
    private final SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DIVIDER = "****************************************************************";
    private static final String LINE_PREFIX = "     | ";
    private static final String LS = lineSeparator();

    //get current timestamp
    //unused because it interferes with the EXPECTED.TXT in runtest
    public String getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timeFormatter.format(timestamp);
    }

    //solution below adapted from https://github.com/se-edu/addressbook-level2/
    public void showWelcomeMessage(String version) {
        getTimeStamp();
        printScreen(
                version,
                //getTimeStamp(),
                DIVIDER,
                LOGO,
                MESSAGE_WELCOME,
                MESSAGE_ACKNOWLEDGE,
                MESSAGE_HELP,
                DIVIDER
        );
    }

    public void printScreen(String... message) {
        for (String m: message) {
            System.out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Print error message.
     * @param meg obtained from DukeException message
     */
    public void showText(String meg) {
        System.out.println(meg);
    }

    public void showNumber(int i) {
        System.out.println(String.valueOf(i));
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printGameMode1Progress(int a, int b) {
        System.out.println("Your progress:" + String.valueOf(a) + "/" + String.valueOf(b));
    }

    public void printSuccess() {
        System.out.println("Finished!");
    }

}

package duke;

import java.util.Scanner;

/**
 * The ui for Duke that is in charge of displaying relevant messages to the user.
 */
public class DukeUi extends Ui {

    /**
     * Presents a farewell message to the user.
     */
    public void printExitMessage() {
        System.out.println(formatMessage("Bye. Hope to see you again soon!\n"));
    }

    /**
     * Scans the user inputs for parser to act upon the user's command.
     *
     * @param parser the parser that interprets user's input.
     */
    public void readUserInput(Parser parser) {
        Scanner scanner = new Scanner(System.in);
        parser.parse(scanner);
        scanner.close();
    }

    /**
     * Presents an welcome message to the user.
     */
    public void printWelcomeMessage() {
        System.out.println(formatMessage("Hello! I'm Peoduo\n" + getIndentation() + "Can I help you?\n"));
    }
}

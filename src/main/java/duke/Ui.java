package duke;

import java.util.Scanner;

/**
 * Represents the user interface for the chat bot.
 * Is Responsible for reading commands and printing messages/errors
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Print messages with many lines
     *
     * @param msgLines message lines
     */
    public void print(String... msgLines) {
        if (msgLines.length == 0) {
            return;
        }
        System.out.println("\t_______________________________________________________");
        for (String msg : msgLines) {
            System.out.println("\t  " + msg);
        }
        System.out.println("\t_______________________________________________________");
    }

    /**
     *Print errors with many lines
     *
     * @param msgLines error lines
     */
    public void printError(String... msgLines) {
        if (msgLines.length == 0) {
            return;
        }
        System.err.println("\t_______________________________________________________");
        System.err.println("\t  *Making angry noise*");
        for (String msg : msgLines) {
            System.err.println("\t  " + msg);
        }
        System.err.println("\t_______________________________________________________");
    }

    /**
     * Print greeting message
     */
    public void greet() {
        print("Yo, I'm Xiri.", "How can I help you?");
    }

    /**
     * Print exit message
     */
    public void bye() {
        print("Ok bye, see you later.");
    }

    /**
     * Read a command from user
     *
     * @return user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}

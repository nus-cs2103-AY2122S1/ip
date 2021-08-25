package duke;

import java.util.Scanner;

/**
 * This class encapsulates the UI of Duke and its interactions with the user.
 *
 * @author Kleon Ang
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for a Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printReply("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints the given string formatted in a Reply.
     *
     * @param string The string to format in a Reply.
     */
    public static void printReply(String string) {
        System.out.println(new Reply(string));
    }

    /**
     * Prints an error message formatted in a Reply for an input file name that does not exist.
     *
     * @param fileName The name of the file that does not exist.
     */
    public void showLoadingError(String fileName) {
        printReply(fileName + " not found. File has been created.");
    }

    /**
     * Returns the next line of the user input.
     *
     * @return A string containing the next line of user input.
     */
    public String nextLine() {
        return scanner.nextLine();
    }
}

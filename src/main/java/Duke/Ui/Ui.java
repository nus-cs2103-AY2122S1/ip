package Duke.Ui;

import java.util.Scanner;

/**
 * The Ui class deals with how Duke interacts with the user.
 *
 * @author cai
 */
public class Ui {
    public static final String GREETING_MESSAGE = "Hello I'm Duke!";
    public static final String EXIT_MESSAGE = "Bye bye! Hope you have a productive day :)";
    public static final String ERROR_MESSAGE = "Oops! An error occurred: %s";
    public static final String FILE_FORMAT_ERROR_MESSAGE = "The file storing your tasks is in an unrecognized format. "
            + "Please fix or remove it.";
    private static final String RULER = "\n````````````````````````````````````````````````````````\n";
    private static final String INPUT_PROMPT = "> ";

    /** System input scanner used for reading the input from the user */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints the given message after formatting and indentation.
     *
     * @param message The message to be printed.
     */
    public static void print(String message) {
        message = RULER + message + RULER;
        String indentedMessage = String.join("\n\t", message.split("\n"));
        System.out.println(indentedMessage);
    }

    /**
     * Reads the input from the user through the standard input stream.
     *
     * @return The input string.
     */
    public static String read() {
        System.out.print(INPUT_PROMPT);
        return scanner.nextLine();
    }
}

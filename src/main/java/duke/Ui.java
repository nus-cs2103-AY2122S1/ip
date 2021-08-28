package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Ui deals with all the interactions with the user.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class Ui {

    protected static final String MESSAGE_BYE = "\t Bye. Hope to see you again soon!";
    protected static final String MESSAGE_INTRO = "Hello! I'm Duke\n\t What can I do for you?";
    protected static final String MESSAGE_LINE = "\t____________________________________________________________";

    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
        printWriter = new PrintWriter(System.out, true);
    }

    /**
     * Prints the given string.
     *
     * @param str the String to be printed
     */
    public void print(String str) {
        printWriter.println(str);
    }

    /**
     * Provides horizontal lines with indentation.
     *
     * @param str the String to be formatted
     */
    protected void reply(String str) {
        print(MESSAGE_LINE);
        print("\t " + str);
        print(MESSAGE_LINE + "\n");
    }

    /**
     * Prints welcome message.
     */
    protected void showWelcome() {
        reply(MESSAGE_INTRO);
    }

    /**
     * Prints bye message.
     */
    protected void showBye() {
        print(MESSAGE_BYE);
        print(MESSAGE_LINE + "\n");
    }

    /**
     * Reads the user input.
     */
    protected String readCommand() throws IOException {
        return bufferedReader.readLine();
    }

    /**
     * Closes the BufferedReader.
     */
    public void close() throws DukeException {
        try {
            showBye();
            inputStreamReader.close();
            bufferedReader.close();
            printWriter.close();
        } catch (IOException e) {
            throw new DukeException("Unable to close the parser.");
        }
    }

    /**
     * Shows the divider line.
     */
    protected void showLine() {
        print(MESSAGE_LINE);
    }

    /**
     * Shows error message.
     */
    protected void showError(String message) {
        print(message);
    }

}

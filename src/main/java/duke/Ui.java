package main.java.duke;

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

    private final InputStreamReader INPUT_STREAM_READER;
    private final BufferedReader BUFFERED_READER;
    private final PrintWriter PRINT_WRITER;

    public Ui() {
        INPUT_STREAM_READER = new InputStreamReader(System.in);
        BUFFERED_READER = new BufferedReader(INPUT_STREAM_READER);
        PRINT_WRITER = new PrintWriter(System.out, true);
    }

    /**
     * Prints the given string.
     *
     * @param str the String to be printed
     */
    public void print(String str) {
        PRINT_WRITER.println(str);
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
        return BUFFERED_READER.readLine();
    }

    /**
     * Closes the BufferedReader.
     */
    public void close() throws DukeException {
        try {
            showBye();
            INPUT_STREAM_READER.close();
            BUFFERED_READER.close();
            PRINT_WRITER.close();
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

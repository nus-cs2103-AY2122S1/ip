package main.java.duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Ui deals with all the interactions with the user.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Ui {

    protected static final String LINE = "\t____________________________________________________________";
    private static final String INTRO = "Hello! I'm Duke\n\t What can I do for you?";
    private static final String BYE_MESSAGE = "\t Bye. Hope to see you again soon!";
    private final InputStreamReader inputStreamReader;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;

    public Ui() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
        printWriter = new PrintWriter(System.out, true);
    }

    /**
     * Print the given string.
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
        print(LINE);
        print("\t " + str);
        print(LINE + "\n");
    }

    /**
     * Print welcome message.
     */
    protected void showWelcome() {
        reply(INTRO);
    }

    /**
     * Print bye message.
     */
    protected void showBye() {
        print(BYE_MESSAGE);
        print(LINE + "\n");
    }

    /**
     * Read the user input.
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
     * Show the divider line.
     */
    protected void showLine() {
        print(LINE);
    }

    /**
     * Show error.
     */
    protected void showError(String message) {
        print(message);
    }

}

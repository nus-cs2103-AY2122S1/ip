package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ui deals with all the interactions with the user.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Ui {

    protected static final String LINE = "\t____________________________________________________________";
    private static final String INTRO = "Hello! I'm Duke\n\t What can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private final InputStreamReader inputStreamReader;
    private final BufferedReader bufferedReader;

    Ui() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    /**
     * Provides horizontal lines with indentation.
     *
     * @param str the String to be formatted
     */
    protected static void reply(String str) {
        System.out.println(LINE);
        System.out.println("\t " + str);
        System.out.println(LINE + "\n");
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
        reply(BYE_MESSAGE);
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
    protected void close() throws DukeException {
        try {
            inputStreamReader.close();
            bufferedReader.close();
            showBye();
        } catch (IOException e) {
            throw new DukeException("Unable to close the parser.");
        }
    }

    /**
     * Show the divider line.
     */
    protected static void showLine() {
        System.out.println(LINE);
    }

    /**
     * Show error.
     */
    protected void showError(String message) {
        reply(message);
    }

}

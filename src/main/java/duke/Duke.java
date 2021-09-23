package duke;

import duke.exception.DukeException;
import duke.util.Status;
import javafx.application.Platform;

/**
 * The Duke program implements an application that reads the user input
 * and does the corresponding actions based on the user input.
 *
 * @author Teng Hon
 */
public class Duke {
    private ChatBot bot;
    private Parser parser;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        bot = new ChatBot();
        parser = new Parser();
        bot.start();
    }

    /**
     * Returns the output depending on the user's input command
     *
     * @param input User input command
     * @return Bot response.
     */
    public String getResponse(String input) {
        String temp;
        try {
            temp = parser.parse(input, bot);
        } catch (DukeException e) {
            return bot.handleErrorMessage(e.getMessage());
        }
        return temp;
    }

    /**
     * Returns the starting message when duke starts up.
     *
     * @return Starting message.
     */
    public String getStart() {
        return bot.handleStart();
    }

    /**
     * Returns the exit status of the program with 0 equals exit and 1 otherwise.
     *
     * @return Int that corresponds to the exit status.
     */
    public Status getExitStatus() {
        return bot.getExitStatus();
    }

    /**
     * Checks if the program should exit before creating a new thread to handle
     * bye message and exit.
     *
     */
    public void exit() {
        if (getExitStatus() == Status.STOP) {
            new Thread() { //inspiration from Lim PeiYuan
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Platform.exit();
                    }
                }
            }.start();
        }
    }
}

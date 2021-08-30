package duke;

import duke.exception.DukeException;

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

    public Duke() {
        bot = new ChatBot();
        parser = new Parser();
        bot.start();
    }

    /**
     * Returns the output depending on the user's input command
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
     * @return Starting message.
     */
    public String getStart() {
        return bot.handleStart();
    }

    public int getExitStatus() {
        return bot.getExitStatus();
    }

    public void exit() {
        if (getExitStatus() == 0) {
            new Thread() {
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

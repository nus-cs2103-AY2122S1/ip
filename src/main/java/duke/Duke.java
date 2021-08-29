package duke;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * The Duke program implements an application that reads the user input
 * and does the corresponding actions based on the user input.
 */
public class Duke {
    private ChatBot bot;
    private Parser parser;

    public Duke() {

        bot = new ChatBot();
        parser = new Parser();
        // create object of Scanner to take inputs
//        Scanner sc = new Scanner(System.in);
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
}

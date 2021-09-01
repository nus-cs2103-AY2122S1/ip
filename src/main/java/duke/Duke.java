package duke;

import duke.parser.Parser;

/**
 * Entry point of Duke Chatbot.
 * Initializes the application and starts the interaction with the user.
 *
 * @author Won Ye Ji
 */
public class Duke {

    /**
     * Runs the program until termination.
     *
     * @param args arguments supplied by the user at program launch.
     */
    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.initialiseDuke();
        parser.runDuke();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

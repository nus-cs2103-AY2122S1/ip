package duke;

import duke.parser.Parser;


/**
 * <code>Duke</code> is the Overarching class for the Duke Bot.
 * It links the back-end Parser with the front-end JavaFX UI.
 */

public class Duke{

    private final Parser parser = new Parser();

    /**
     * <code>getResponse(String input)</code> parses an input string using the Duke parser
     * It then runs back-end code and returns an output response String to show to the user.
     *
     * @param input String input by the user in the JavaFX GUI
     * @return response returned by Duke after parsing the user input
     */

    public String getResponse(String input) {
        return parser.parseCommand(input);
    }
    
}
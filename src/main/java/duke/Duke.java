package duke;

import duke.parser.Parser;


/**
 * <code>Duke</code> is the Overarching class for the Duke Bot.
 * It links the back-end Parser with the front-end JavaFX UI.
 */

public class Duke{

    private Parser parser = new Parser();

    public String getResponse(String input) {

        String output = parser.parseCommand(input);
        return output;
    }
    
}
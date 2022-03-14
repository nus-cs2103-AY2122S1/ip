package duke;

import duke.parser.Parser;
import duke.task.TaskList;

import java.util.Locale;

/**
 * Represents an instance of a duke, which is our program that
 * interacts with users based on the user's input.
 *
 * @author yeo-yiheng
 */
public class Duke {
    private static final TaskList TASK = new TaskList();
    private static final Parser PARSER = new Parser();

    /**
     * Loads the file contents into an arraylist.
     */
    public void loadArray() {
        TASK.loadArrayList();
    }

    /**
     * Retrieves the response from the program based on the user's
     * input.
     *
     * @param input the user input
     * @return response from the program
     */
    public String getResponse(String input) {
        String response = feedIntoParser(input);
        return "Duke: " + response;
    }

    /**
     * Feeds the user's input into the parser for processing.
     *
     * @param command the user input
     * @return response received by the program
     */
    private String feedIntoParser(String command) {
        return PARSER.parse(command.toLowerCase(Locale.ROOT));
    }

    /**
     * A function that does nothing at all.
     */
    public static void nullFunction() {}

}

package duke.logic;

import duke.exception.DukeInvalidCommandException;
import duke.logic.parser.Parser;
import duke.logic.tasks.TaskList;

import java.util.Locale;

/**
 * Main program
 */
public class Duke {
    private Parser parser;
    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser(taskList);
    }

    /**
     * Processes the user input and obtains the response.
     *
     * @param userCommandText The user input.
     * @return A string describing the program's response to the user input.
     */
    public String getResponse(String userCommandText) {
        String result;
        try {
            result = parser.invokeCommand(userCommandText);
        } catch (DukeInvalidCommandException e) {
            result = e.getMessage();
        }
        return result;
    }
}

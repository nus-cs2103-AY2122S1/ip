package duke.io;

import duke.command.Commands;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Parser to parse user input and process it.
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input for the first word, then calls the appropriate function for the command.
     *
     * @param input User input.
     * @return The response to be sent to the user.
     */
    public String getResponse(String input) {
        String firstWord = input.split(" ")[0];

        // if nothing matches, response stays like this
        String response = "Unsupported Operation!";

        try {
            for (Commands command : Commands.values()) {
                if (command.isCommand(firstWord)) {
                    response = command.getCommand().parse(input, taskList);
                    // terminate out of loop once the command is found
                    break;
                }
            }
        } catch (DukeException e) {
            response = e.displayError();
        }

        return response;
    }
}

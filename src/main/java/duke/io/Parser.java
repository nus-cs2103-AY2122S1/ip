package duke.io;

import duke.command.Command;
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

        Command command = Commands.findCommand(firstWord);

        if (command == null) {
            return "Unsupported Operation!";
        }

        try {
            return command.parse(input, taskList);
        } catch (DukeException e) {
            return e.displayError();
        }
    }
}

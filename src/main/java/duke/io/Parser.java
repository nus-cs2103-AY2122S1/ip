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

        Commands[] commands = Commands.values();
        int i = 0;

        // Find the first command that matches
        while (i != commands.length && !commands[i].isCommand(firstWord)) {
            i++;
        }

        // None of the commands match
        if (i == commands.length) {
            return "Unsupported Operation!";
        }

        try {
            return commands[i].getCommand().parse(input, taskList);
        } catch (DukeException e) {
            return e.displayError();
        }
    }
}

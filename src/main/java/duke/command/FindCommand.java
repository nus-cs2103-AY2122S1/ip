package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Command to find a task that matches the search string.
 */
public class FindCommand extends Command {
    public FindCommand() {
        setMainCommand("find");
    }

    /**
     * Parses the user input for a search string.
     * Then finds all tasks that contain the search string and lists them.
     *
     * @param input Full user input.
     * @param taskList The list of tasks.
     * @return The response which lists all tasks that match the search string.
     * @throws DukeException Exception thrown when search string is empty.
     */
    @Override
    public String parse(String input, TaskList taskList) throws DukeException {
        assert taskList != null : "taskList should not be null";

        int firstSpace = input.indexOf(' ');

        // No space after the command
        if (firstSpace == -1) {
            throw new DukeException("Please input a search string.");
        }

        String data = input.substring(firstSpace).strip();

        if (data.equals("")) {
            throw new DukeException("Please input a search string.");
        }

        return taskList.find(data);
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Command to find a task that matches the search string
 */
public class FindCommand extends Command {
    public FindCommand() {
        setCommandString("find");
    }

    /**
     * Parses the user input for a search string,
     * then finds all tasks that contain the search string and lists them
     *
     * @param input Full user input
     * @param taskList The list of tasks
     * @return The response
     * @throws DukeException Exception thrown when search string is empty
     */
    @Override
    public String parse(String input, TaskList taskList) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input a search string.");
        }

        String data = input.substring(getCommandLength()).strip();

        if (data.equals("")) {
            throw new DukeException("Please input a search string.");
        }

        return taskList.find(data);
    }
}

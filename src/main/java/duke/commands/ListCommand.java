package duke.commands;

import duke.DukeException;
import duke.TaskList;

/**
 * Class that represents the command to display the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command.
     * @param taskList The list of tasks to be displayed.
     * @return Response to be displayed in the GUI.
     * @throws DukeException If there are no tasks to be displayed.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        if (taskList.getLength() == 0) {
            throw new DukeException("Your task list is empty :(");
        } else {
            assert taskList.getLength() > 0 : "Task list has an invalid length";
            return "Here's your list!:\n" + taskList.toString();
        }
    }
}

package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class SortCommand extends Command {
    /**
     * Executes the command.
     * @param taskList The list of tasks to be sorted and displayed.
     * @return Response to be displayed in the GUI.
     * @throws DukeException If there are no tasks to be displayed.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        if (taskList.getLength() == 0) {
            throw new DukeException("There's nothing to sort :(");
        } else {
            assert taskList.getLength() > 0 : "Task list has an invalid length";
            return taskList.sortByTask();
        }
    }
}

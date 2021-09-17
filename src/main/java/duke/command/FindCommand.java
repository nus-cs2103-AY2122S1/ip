package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, String input, Storage storage) throws DukeException {
        try {
            if (input.replaceAll("\\s", "").length() == 4) {
                throw new DukeException(DukeException.Type.EmptyFind);
            } else {
                String query = input.substring(5).toLowerCase();
                TaskList matchingTasks = taskList.findTasks(query);
                return Ui.printTasksFound(matchingTasks);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, String input, Storage storage) throws DukeException {
        try {
            if (input.replaceAll("\\s", "").length() <= 7) {
                throw new DukeException(DukeException.Type.EmptyUpdate);
            } else {
                return processUpdate(taskList, input, storage);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private String processUpdate(TaskList taskList, String input, Storage storage) {
        try {
            int taskNum = Integer.parseInt(input.substring(7, 8));
            if (taskList.getNumTasks() < taskNum) {
                throw new DukeException(DukeException.Type.InvalidTaskNumber);
            } else {
                Task task = taskList.updateTask(taskNum, input.substring(9), storage);
                return Ui.printTaskUpdated(task);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

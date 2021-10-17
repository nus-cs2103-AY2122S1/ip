package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, String input, Storage storage) throws DukeException {
        try {
            if (input.replaceAll("\\s", "").length() == 6) {
                throw new DukeException(DukeException.Type.EmptyDelete);
            } else {
                return processDelete(taskList, input, storage);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Deletes the given task, saves task list to storage and returns confirmation string.
     *
     * @param taskList the list of user tasks.
     * @param input the user's input;
     * @param storage the storage which holds all the user's data.
     * @return
     */
    private String processDelete(TaskList taskList, String input, Storage storage) {
        try {
            int index = Integer.parseInt(input.substring(7));
            if (index < 1 || taskList.getNumTasks() < index) {
                throw new DukeException(DukeException.Type.InvalidTaskNumber);
            } else {
                Task task = taskList.removeTask(index, storage);
                return Ui.printTaskDeleted(task, taskList.getNumTasks());
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

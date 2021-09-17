package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, String input, Storage storage) {
        try {
            if (input.replaceAll("\\s", "").length() == 4) {
                throw new DukeException(DukeException.Type.EmptyDone);
            } else {
                return processDone(taskList, input, storage);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks the task as done, saves task list to storage and returns confirmation string.
     *
     * @param taskList the list of user tasks.
     * @param input the user's input;
     * @param storage the storage which holds all the user's data.
     * @return
     */
    private String processDone(TaskList taskList, String input, Storage storage) {
        try {
            int index = Integer.parseInt(input.substring(5));
            if (index < 1 || taskList.getNumTasks() < index) {
                throw new DukeException(DukeException.Type.InvalidTaskNumber);
            } else {
                Task task = taskList.markTaskAsDone(index, storage);
                return Ui.printTaskDone(task);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 *
 * Remove a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a command that deletes a task from the task list.
     *
     * @param taskNumber task number to be deleted
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Delete tasks associated to task number. Task number is equivalent to the index
     * of the task in task list + 1.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @param ui ui instance initialised when duke is created.
     * @throws DukeException task number is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        try {
            Task task = taskList.deleteTask(taskNumber);
            storage.writeToDisk(taskList.compileTasks());
            String response = String
                    .format("Ooh yeah! Task %d deleted:\n  %s\nNow you have %d tasks in the list.",
                            taskNumber,
                            task,
                            taskList.getSize());
            ui.respond(response);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }
}

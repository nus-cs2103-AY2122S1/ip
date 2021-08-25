package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Mark a task in the task list as done.
 */
public class DoneCommand extends Command {
    private final int taskNumber;

    /**
     * Construct a command that marks a task as complete.
     *
     * @param taskNumber task to be marked as complete.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks tasks associated to task number. Task number is equivalent to the index
     * of the task in task list + 1.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @param ui ui instance initialised when duke is created.
     * @throws DukeException task number is invalid
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        try {
            Task task = taskList.markAsDone(this.taskNumber);
            storage.writeToDisk(taskList.compileTasks());
            String response = String.format("Ooh yeah! Task %d marked as done:\n  %s",
                    taskNumber,
                    task);
            ui.respond(response);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }
}

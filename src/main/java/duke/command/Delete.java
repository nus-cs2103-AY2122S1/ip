package duke.command;

import duke.exception.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a delete commands that deals with deleting a specific task from the task list.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class Delete extends DukeCommand {
    private final int taskNumber;

    /**
     * Constructor for a Delete Command.
     *
     * @param ui         The Ui handler that handles the printing of message with respect to the command.
     * @param storage    The storage handler that handles saving or loading data to local directory.
     * @param list       The TaskList handler that handles operation related to task.
     * @param taskNumber The task number that corresponds to the task that user want to delete.
     */
    public Delete(Ui ui, Storage storage, TaskList list, int taskNumber) {
        super(ui, storage, list);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Delete command. Deletes task to the task list and
     * prints a message after deleting, and update the local data file.
     *
     * @throws NoSuchTaskException When the task number is invalid.
     */
    @Override
    public void execute() throws NoSuchTaskException {
        Task t = list.delete(taskNumber);
        ui.deleteTaskMessage(list.size(), t);
        storage.save(list.getList());
    }
}

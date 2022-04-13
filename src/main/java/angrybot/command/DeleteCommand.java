package angrybot.command;

import angrybot.exception.NoSuchTaskException;
import angrybot.storage.Storage;
import angrybot.task.Task;
import angrybot.task.TaskList;
import angrybot.ui.Ui;

/**
 * Encapsulates a delete commands that deals with deleting a specific task from the task list.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class DeleteCommand extends AngryBotCommand {
    private final int taskNumber;

    /**
     * Constructor for a Delete Command.
     *
     * @param ui         The Ui handler that handles the printing of message with respect to the command.
     * @param storage    The storage handler that handles saving or loading data to local directory.
     * @param list       The TaskList handler that handles operation related to task.
     * @param taskNumber The task number that corresponds to the task that user want to delete.
     */
    public DeleteCommand(Ui ui, Storage storage, TaskList list, int taskNumber) {
        super(ui, storage, list);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Delete command. Deletes task to the task list and
     * prints a message after deleting, and update the local data file.
     *
     * @return A message to show on the GUI that the task has been deleted.
     * @throws NoSuchTaskException When the task number is invalid.
     */
    @Override
    public String execute() throws NoSuchTaskException {
        try {
            Task t = list.delete(taskNumber);
            storage.save(list.getList());
            return ui.deleteTaskMessage(list.size(), t);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }
}

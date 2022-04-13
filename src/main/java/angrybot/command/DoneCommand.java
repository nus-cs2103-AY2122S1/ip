package angrybot.command;

import angrybot.exception.NoSuchTaskException;
import angrybot.storage.Storage;
import angrybot.task.TaskList;
import angrybot.ui.Ui;

/**
 * Encapsulates a Done commands that deals with marking a task in the task list as done.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class DoneCommand extends AngryBotCommand {
    private final int taskNumber;

    /**
     * Constructor for a Done Command.
     *
     * @param ui         The Ui handler that handles the printing of message with respect to the command.
     * @param storage    The storage handler that handles saving or loading data to local directory.
     * @param list       The TaskList handler that handles operation related to task.
     * @param taskNumber The task number that corresponds to the task that user want to mark as done.
     */
    public DoneCommand(Ui ui, Storage storage, TaskList list, int taskNumber) {
        super(ui, storage, list);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Done command. Marks a task in the task list as done,
     * prints a message after marking, and update the local data file.
     *
     * @return A message to show on the GUI that the task has been marked as done.
     * @throws NoSuchTaskException When the task number is invalid.
     */
    @Override
    public String execute() throws NoSuchTaskException {
        try {
            storage.save(list.getList());
            return ui.markDoneMessage(list.mark(taskNumber));
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }
}

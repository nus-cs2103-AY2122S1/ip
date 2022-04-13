package angrybot.command;

import angrybot.storage.Storage;
import angrybot.task.Task;
import angrybot.task.TaskList;
import angrybot.ui.Ui;

/**
 * Encapsulates an Add command that deals with adding task to the task list.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class AddCommand extends AngryBotCommand {
    private final Task task;

    /**
     * Constructor for an Add command.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     * @param task    The task to be added to the task list.
     */
    public AddCommand(Ui ui, Storage storage, TaskList list, Task task) {
        super(ui, storage, list);
        this.task = task;
    }

    /**
     * Executes the Add command. Adds task to the task list and
     * prints a message after adding, and update the local data file.
     * @return A message to show on the GUI that the task has been added.
     */
    @Override
    public String execute() {
        list.add(task);
        storage.save(list.getList());
        return ui.addTaskMessage(list.size(), task);
    }
}

package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates an Add command that deals with adding task to the task list.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class Add extends DukeCommand {
    private final Task task;

    /**
     * Constructor for an Add command.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     * @param task    The task to be added to the task list.
     */
    public Add(Ui ui, Storage storage, TaskList list, Task task) {
        super(ui, storage, list);
        this.task = task;
    }

    /**
     * Executes the Add command. Adds task to the task list and
     * prints a message after adding, and update the local data file.
     */
    @Override
    public void execute() {
        list.add(task);
        ui.addTaskMessage(list.size(), task);
        storage.save(list.getList());
    }
}

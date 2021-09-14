package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Represents a delete command. A <code>DeleteCommand</code> describes
 * the action to be executed when a user indicates a task to delete.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:\n";
    private int index;
    private Storage storage;

    /**
     * Public constructor for a <code>DeleteCommand</code>.
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The task list to be updated.
     * @param index Index of task to be deleted.
     * @param storage The storage to handle modifications to the file.
     */
    public DeleteCommand(Ui ui, TaskList taskList,
                         int index, Storage storage) {
        super(ui, taskList);
        this.index = index;
        this.storage = storage;
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    @Override
    public String getUsageMessage() {
        return "delete <number> | delete the task indexed by the number as done";
    }

    /**
     * Check if the given command alters the task list.
     *
     * @return true if it updates the task list.
     */
    @Override
    public boolean isUpdatesTaskList() {
        return true;
    }

    /**
     * Deletes the task from the task list.
     */
    @Override
    public String execute() throws DukeException {
        boolean isValid = taskList.isValidTaskIndex(index);
        if (isValid) {
            Task task = taskList.getTask(index);
            taskList = taskList.deleteTask(index);

            storage.deleteTaskFromFile(this.taskList);

            return String.format("%s\n%s\n%s",
                    DELETE_MESSAGE, task, taskList.status());
        } else {
            throw new DukeException("There is no such task.");
        }
    }

}


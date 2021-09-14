package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Represents a done command. A <code>DoneCommand</code> describes
 * the action to be executed when a user indicates a task to be marked
 * as done.
 */
public class DoneCommand extends Command {
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private int index;
    private Storage storage;

    /**
     * Public constructor for a <code>DoneCommand</code>.
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The task list to be updated.
     * @param index Index of task to be marked as done.
     * @param storage The storage to handle modifications to the file.
     */
    public DoneCommand(Ui ui, TaskList taskList, int index, Storage storage) {
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
        return "done <number> | mark the task indexed by the number as done";
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
     * Mark the task as done in the task list.
     */
    @Override
    public String execute() throws DukeException {
        boolean isValid = taskList.isValidTaskIndex(index);
        if (isValid) {
            String toUpdate = taskList.getTask(index).toString();
            taskList = taskList.markTaskAsCompleted(index);

            Task task = taskList.getTask(index);
            storage.markTaskAsCompleted(task.toString(), toUpdate);

            return String.format("%s\n%s\n%s",
                    DONE_MESSAGE, task, taskList.status());
        } else {
            throw new DukeException("There is no such task.");
        }
    }

}


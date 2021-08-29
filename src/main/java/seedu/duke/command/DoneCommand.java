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

    @Override
    public String getUsageMessage() {
        return "done <number> | mark the task indexed by the number as done";
    }

    @Override
    public boolean updatesTaskList() {
        return true;
    }

    /**
     * Mark the task as done in the task list.
     */
    @Override
    public void execute() throws DukeException {
        boolean isValid = taskList.isValidTaskIndex(index);
        if (isValid) {
            String toUpdate = this.taskList.getTask(index).toString();
            Task task = this.taskList.markTaskAsCompleted(index);

            ui.divide();
            ui.outputMessage(DONE_MESSAGE);
            ui.outputMessage(task.toString());
            ui.outputMessage(taskList.status());
            ui.divide();

            storage.markTaskAsCompleted(task.toString(), toUpdate);
        } else {
            throw new DukeException("There is no such task.");
        }
    }

}


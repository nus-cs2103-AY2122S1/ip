package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

/**
 * The class that models a delete command.
 */
public class CommandDelete extends Command {
    private int index;

    /**
     * Instantiates an object with given index to delete the task when
     * called to execute.
     * @param index The index where the task should be deleted.
     */
    public CommandDelete(int index) {
        super();
        this.index = index;
    }

    /**
     * Execute the delete action and auto-saves to the file when called.
     * @param tasks The taskList to delete the task.
     * @param ui The Ui object to print messages after action.
     * @param storage The Storage object that auto-saves after modification.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String msg = tasks.deleteTask(index);
            tasks.saveToFile(storage);
            ui.printMsg(msg);
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
        }
    }
}

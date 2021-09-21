package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The class that models a mark as done command.
 */
public class CommandDone extends Command {
    int index;

    /**
     * Instantiates an object with given index to mark the task as done when
     * called to execute.
     *
     * @param index The index where the task should be deleted.
     */
    public CommandDone(int index) {
        this.index = index;
    }


    /**
     * Execute the mark as done action and auto-saves to the file when called.
     *
     * @param tasks   The taskList to mark the task done.
     * @param ui      The Ui object to print messages after action.
     * @param storage The Storage object that auto-saves after modification.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String msg = tasks.markTaskDone(index);
            tasks.saveToFile(storage);
            return msg;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}

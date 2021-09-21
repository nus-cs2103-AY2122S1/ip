package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The class that models an add command.
 */
public class CommandAdd extends Command {
    Task taskToAdd;


    /**
     * Instantiates an object with given task to be added when
     * called to execute.
     *
     * @param taskToAdd
     */
    public CommandAdd(Task taskToAdd) {
        super();
        this.taskToAdd = taskToAdd;
    }


    /**
     * Execute the add action and auto-saves to the file when called.
     *
     * @param tasks   The taskList to add new task in.
     * @param ui      The Ui object to print messages after action.
     * @param storage The Storage object that auto-saves after modification.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String msg = tasks.addTask(taskToAdd);
            tasks.saveToFile(storage);
            return msg;
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    /**
     * String representation of CommandAdd.
     * Shows the command type and task to add.
     *
     * @return String representation of the CommandAdd.
     */

    @Override
    public String toString() {
        return String.format("CommandAdd taskToAdd: %s", taskToAdd);
    }
}

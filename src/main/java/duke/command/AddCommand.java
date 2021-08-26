package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Representation for the add command of Duke.
 */
public class AddCommand extends Command{
    private final Task toAdd;

    /**
     * Constructor for AddCommand.
     *
     * @param toAdd Task to add to taskList.
     */
    public AddCommand(Task toAdd){
        this.toAdd = toAdd;
    }

    /**
     * Checks if AddCommand is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the AddCommand.
     *
     * @param tasks TaskList object to add toAdd to.
     * @param ui Ui Object to print to user.
     * @param storage Storage object which saves and loads the taskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.toAdd);
        ui.printAdd(this.toAdd, tasks.getSize());
    }
}

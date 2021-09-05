package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Representation for the add command of Duke.
 */
public class AddCommand extends Command {
    private final Task toAdd;

    /**
     * Constructor for AddCommand.
     *
     * @param toAdd Task to add to taskList.
     */
    public AddCommand(Task toAdd) {
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
     * @param taskList TaskList object to add toAdd to.
     * @param ui Ui Object to print to user.
     * @param storage Storage object which saves and loads the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.toAdd);
        ui.printAdd(this.toAdd, taskList.getSize());
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the adding of the task.
     *
     * @param taskList TaskList object to add toAdd to.
     * @param ui Ui Object to get the String representation from.
     * @param storage Storage object which saves and loads the taskList.
     * @return String representation of the things printed in the execute method.
     */
    @Override
    public String getExecutedString(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.toAdd);
        return ui.getPrintAddString(this.toAdd, taskList.getSize());
    }
}

package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

/**
 * The ListCommand implements Command and handles
 * displayed the list of tasks to the user
 */
public class ListCommand implements Command {

    /**
     * Overwritten Execute method. It prints the curr list of tasks.
     *
     * @param taskList TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output(taskList.printList());
    }
}

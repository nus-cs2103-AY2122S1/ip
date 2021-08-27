package Duke;

/**
 * This class encapsulates the command to display the current tasks.
 * It is triggered by the parser and uses the TaskManager and Ui.
 */
public class ListTasksCommand implements ICommand {

    /**
     * Calls the Ui object to display the current list of tasks.
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param ui The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        ui.printTasks(tm.getTasks());
    }

}
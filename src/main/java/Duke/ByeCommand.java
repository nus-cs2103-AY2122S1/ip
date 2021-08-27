package Duke;

/**
 * This class encapsulates the command to cease the conversation with Duke.
 * It is triggered by the parser and uses the Ui.
 */
public class ByeCommand implements ICommand {

    /**
     * Called the Ui object to display the 'bye' message then ends the program.
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param ui The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        ui.printByeMessage();
        System.exit(0);
    }

}
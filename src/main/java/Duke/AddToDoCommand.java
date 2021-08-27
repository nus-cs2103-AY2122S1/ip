package Duke;

/**
 * This class encapsulates the command to add a To-Do type task.
 * It is triggered by the parser and uses the TaskManager, Ui, and Storage.
 */
public class AddToDoCommand implements ICommand {

    private final String INPUT;

    /**
     * Constructor for the command.
     * @param input The user's input which triggered the creation of this command.
     */
    public AddToDoCommand(String input) {
        this.INPUT = input;
    }

    /**
     * Adds the to-do task by interacting with the relevant instances as mentioned above.
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param ui The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        try {
            Task addedTask = tm.addToDo(INPUT);
            ui.printTaskAddition(addedTask, tm.getTasks().size());
            storage.updateSave(tm.getTasks());
        } catch (DukeException.NoNameException e) {
            ui.printErrorMessage(e);
        }
    }
}
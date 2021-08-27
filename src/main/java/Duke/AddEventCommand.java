package Duke;

/**
 * This class encapsulates the command to add an event type task.
 * It is triggered by the parser and uses the TaskManager, Ui, and Storage.
 */
public class AddEventCommand implements ICommand {

    private final String input;

    /**
     * Constructor for the command.
     * @param input The user's input which triggered the creation of this command.
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Adds the event task by interacting with the relevant instances as mentioned above.
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param ui The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        try {
            Task addedEvent = tm.addEvent(input);
            if (addedEvent != null) {
                ui.printTaskAddition(addedEvent, tm.getTasks().size());
                storage.updateSave(tm.getTasks());
            } else {
                throw new DukeException.NoTimeSpecifiedException("");
            }
        } catch (DukeException.NoNameException |
                DukeException.NoTimeSpecifiedException e) {
            ui.printErrorMessage(e);
        }
    }
}
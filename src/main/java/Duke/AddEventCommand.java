package Duke;

public class AddEventCommand implements ICommand {

    private final String input;

    public AddEventCommand(String input) {
        this.input = input;
    }

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
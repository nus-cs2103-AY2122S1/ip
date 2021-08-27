package Duke;

public class AddDeadlineCommand implements ICommand {

    private final String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    public void execute(TaskManager tm, Ui ui, Storage storage) {
        try {
            Task addedDeadline = tm.addDeadline(input);
            if (addedDeadline != null) {
                ui.printTaskAddition(addedDeadline, tm.getTasks().size());
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
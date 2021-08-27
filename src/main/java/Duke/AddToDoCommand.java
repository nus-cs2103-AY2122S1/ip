package Duke;

public class AddToDoCommand implements ICommand {

    private final String INPUT;

    public AddToDoCommand(String input) {
        this.INPUT = input;
    }

    public void execute(TaskManager tm, Ui ui, Storage storage) {
        try {
            Task addedTask = tm.addToDo(INPUT);
            ui.printTaskAddition(addedTask, tm.getTasks().size());
            storage.updateSave(tm.getTasks());
        } catch (DukeException.EmptyTaskException | DukeException.NoNameException e) {
            ui.printErrorMessage(e);
        }
    }
}
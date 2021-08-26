public class AddToDoCommand implements ICommand {

    private final String input;

    public AddToDoCommand(String input) {
        this.input = input;
    }

    public void execute(TaskManager tm, Ui ui, Storage storage) {
        try {
            Task addedTask = tm.addToDo(input);
            ui.printTaskAddition(addedTask, tm.getTasks().size());
            storage.updateSave(tm.getTasks());
        } catch (DukeException.EmptyTaskException | DukeException.NoNameException e) {
            ui.printErrorMessage(e);
        }
    }
}
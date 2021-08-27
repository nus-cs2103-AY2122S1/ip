package Duke;

public class UnknownCommand implements ICommand {
    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        ui.printUnknownCommandMessage();
    }
}

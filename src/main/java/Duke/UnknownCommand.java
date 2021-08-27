package Duke;

/**
 * This class is used when an input is not understood.
 */
public class UnknownCommand implements ICommand {
    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        ui.printUnknownCommandMessage();
    }
}

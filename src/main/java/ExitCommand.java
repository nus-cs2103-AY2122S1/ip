public class ExitCommand extends Command {
    public ExitCommand() {
    }

    public void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        ui.showGoodbyeMessage();
        storage.save(taskList);
    }

    public boolean isExit() {
        return true;
    }
}
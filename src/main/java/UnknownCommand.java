public class UnknownCommand implements Command {

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.printUnknownCommand();
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}

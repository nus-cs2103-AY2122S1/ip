public class HelpCommand implements Command {

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.printHelp();
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}

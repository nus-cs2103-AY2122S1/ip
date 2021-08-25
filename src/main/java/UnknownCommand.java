public class UnknownCommand extends Command{

    UnknownCommand() {

    }

    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showInvalidCommand();
    }

    public boolean isExit() {
        return false;
    }
}

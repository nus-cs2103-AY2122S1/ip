public class ByeCommand extends Command{

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.stopMethod();
    }
}

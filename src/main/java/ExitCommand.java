public class ExitCommand extends Command{
    public ExitCommand() {}

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
        storage.saveToStorage(taskList.getTasks());
    }
}
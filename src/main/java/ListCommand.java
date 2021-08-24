public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printAll(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
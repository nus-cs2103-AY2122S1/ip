public class ListTasks extends Command {

    public ListTasks() {}

    @Override
    void execute(ToDo taskList, Ui ui, Storage storage) {
        ui.showListItems(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
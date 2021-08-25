public class ListTasksCommand extends Command {

    public ListTasksCommand() {}

    @Override
    void execute(ToDo taskList, Ui ui, Storage storage) {
        ui.showListItems(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
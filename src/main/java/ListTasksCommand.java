public class ListTasksCommand implements ICommand {

    public void execute(TaskManager tm, Ui ui, Storage storage) {
        ui.printTasks(tm.getTasks());
    }

}
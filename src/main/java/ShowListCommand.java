public class ShowListCommand implements Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasks(taskList.getTasks());
    }
}

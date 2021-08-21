public class List extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print(taskList.stringifyTasksForList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

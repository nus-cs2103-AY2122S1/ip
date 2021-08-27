public class DoneCommand extends Command{
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task currTask = taskList.get(index);
        currTask.markAsDone();
        ui.doneTask(currTask);
    }
}

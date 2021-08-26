public class DoneCommand implements Command {

    private int startOfString;

    public DoneCommand(int startOfString) {
        this.startOfString = startOfString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskToChange = taskList.retrieveTask(startOfString);
        storage.changeDone(taskToChange);
        ui.markAsDone(taskToChange);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

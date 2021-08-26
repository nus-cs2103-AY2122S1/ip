public class DeleteCommand implements Command {

    private int startOfString;

    public DeleteCommand(int startOfString) {
        this.startOfString = startOfString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskToDelete = taskList.deleteTask(startOfString);
        storage.deleteFromFile(taskToDelete);
        ui.markAsDeleted(taskToDelete);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

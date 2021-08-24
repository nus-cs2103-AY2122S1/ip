public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber > tasks.getSize() || taskNumber <= 0) {
            throw new DukeException("Please insert a valid Task Number!");
        } else {
            tasks.deleteTask(taskNumber);
            storage.save(tasks);
            ui.showMessage("Task deleted!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

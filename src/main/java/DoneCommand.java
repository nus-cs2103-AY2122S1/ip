public class DoneCommand extends Command{
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber > tasks.getSize() || taskNumber <= 0) {
            throw new DukeException("Please insert a valid Task Number!");
        } else {
            tasks.getTask(taskNumber).markAsDone();
            storage.save(tasks);
            ui.showMessage("Task marked as done!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

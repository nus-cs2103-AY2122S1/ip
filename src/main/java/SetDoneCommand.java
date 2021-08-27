public class SetDoneCommand extends Command {
    private int taskIndex;

    public SetDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException{
        if (tasks.getLength() <= taskIndex || 0 > taskIndex) {
            throw new DukeException("Invalid task index provided!");
        }
        Task currTask = tasks.getTask(taskIndex);
        currTask.markAsDone();
        storage.updateTasks(tasks);
        ui.showMarkedAsDone(currTask);
    }
}

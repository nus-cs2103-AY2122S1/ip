public class DoneCommand extends Command {
    private Integer taskNum;

    public DoneCommand(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.markTaskDone(taskNum);

        ui.showMessage("Good work! I've marked this task as done:");
        ui.showMessage(task.toString());
        
        storage.save(tasks.getListData());
    }

    public boolean isExit() {
        return false;
    }
}

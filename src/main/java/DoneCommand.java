public class DoneCommand extends Command {
    private final int taskIndex;

    DoneCommand(int index) {
        this.taskIndex = index;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        if (taskIndex <= 0 || taskIndex > task.getNumTasks() || task.isEmptyTaskList()) {
            String errorMessage = String.format("   INDEX ERROR: Task number %d does not exist to be completed", taskIndex);
            throw new InvalidInputException(errorMessage);
        } else {
            String completedTaskInfo = task.completeTask(taskIndex);
            ui.showCompletedTask(completedTaskInfo);
        }
    }

    public boolean isExit() {
        return false;
    }
}

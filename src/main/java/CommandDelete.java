public class CommandDelete extends DukeCommand {
    private int taskId;

    public CommandDelete(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tl) {
        DukeUi.printLine(tl.deleteTask(taskId));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

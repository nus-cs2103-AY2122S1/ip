public class CommandDone extends DukeCommand {
    private int taskId;

    public CommandDone(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tl) {
        DukeUi.printLine(tl.markComplete(taskId));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

public class DoneCommand extends Command{

    private final int taskId;
    public static final String COMMAND_WORD = "done";

    DoneCommand(TaskList taskList, int taskId) {
        super(taskList);
        this.taskId = taskId;
    }

    @Override
    public CommandResult execute() throws DukeException {
        TaskList taskList = super.getTaskList();
        Task completedTask = taskList.markAsCompleted(this.taskId);
        return new CommandResult("Nice! I've marked this task as done:\n "
                + completedTask.details(), false);
    }
}

public class DeleteCommand extends Command implements ListNumberPrintable{

    private final int taskId;
    public static final String COMMAND_WORD = "delete";

    DeleteCommand(TaskList taskList, int taskId) {
        super(taskList);
        this.taskId = taskId;
    }

    @Override
    public String printListNumber(TaskList taskList) {
        return "You now have "
                + taskList.size() + " tasks in the list.";
    }

    @Override
    public CommandResult execute() throws DukeException {
        TaskList taskList = super.getTaskList();
        Task deletedTask = taskList.deleteTask(this.taskId);
        return new CommandResult("Noted. I've removed this task:\n "
                + deletedTask.details()
                + "\n"
                + printListNumber(taskList),
                false);
    }
}

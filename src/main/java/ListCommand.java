public class ListCommand extends Command{

    public static final String COMMAND_WORD = "list";

    ListCommand(TaskList taskList) {
        super(taskList);
    }

    @Override
    public CommandResult execute() {
        TaskList taskList = super.getTaskList();
        String feedback = "Here are the tasks in your list:\n" + taskList;
        return new CommandResult(feedback, false);
    }
}

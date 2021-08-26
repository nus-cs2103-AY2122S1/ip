package duke.commands;

public class DeleteCommand extends Command {
    private final int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public CommandResult execute() {
        if (taskNo > taskList.size() || taskNo <= 0) {
            String msg = taskList.size() == 0
                    ? "You don't have any tasks!"
                    : "Invalid take number! Must be between 1 and " + taskList.size();
            return new CommandResult(msg);
        }
        return new CommandResult("Noted. I've deleted this task:\n  " + taskList.delete(taskNo));
    }
}

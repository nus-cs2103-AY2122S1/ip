package duke.commands;

public class DoneCommand extends Command {
    private final int taskNo;

    public DoneCommand(int taskNo) {
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
        return (taskList.markAsDone(taskNo))
                ? new CommandResult("I've marked this task as done:\n  " + taskList.get(taskNo))
                : new CommandResult("Task already done.");
    }
}

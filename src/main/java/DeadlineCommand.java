import java.time.LocalDateTime;

class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(String taskDescription, LocalDateTime by) {
        deadline = new Deadline(taskDescription, by);
    }

    @Override
    public CommandResult execute() {
        taskList.add(deadline);
        return new CommandResult("I've added:\n  " + deadline);
    }
}

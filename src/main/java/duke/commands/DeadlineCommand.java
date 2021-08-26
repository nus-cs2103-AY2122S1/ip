package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Deadline;

public class DeadlineCommand extends Command {
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

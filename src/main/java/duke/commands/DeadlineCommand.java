package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Deadline;

/**
 * Adds a new deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Creates a command to add a new deadline task.
     *
     * @param taskDescription Description of the deadline task to be added.
     * @param by The deadline for the task.
     */
    public DeadlineCommand(String taskDescription, LocalDateTime by) {
        deadline = new Deadline(taskDescription, by);
    }

    /**
     * Adds the new deadline task to the task list.
     *
     * @return The execution result.
     */
    @Override
    public CommandResult execute() {
        taskList.add(deadline);
        return new CommandResult("I've added:\n  " + deadline);
    }
}

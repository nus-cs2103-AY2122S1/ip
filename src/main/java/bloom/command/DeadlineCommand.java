package bloom.command;

import java.time.LocalDateTime;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Deadline;

/**
 * Represents a deadline command which
 * creates a deadline task with given description
 * with date and time.
 */
public class DeadlineCommand extends Command {

    /** The description of the deadline. */
    private final String description;

    /** The date and time of the deadline. */
    private final LocalDateTime by;

    /**
     * Constructor for a DeadlineCommand.
     *
     * @param description the description of the deadline
     * @param by          the date and time of the deadline
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Creates a deadline task.
     */
    @Override
    public String run() {
        Deadline deadline = new Deadline(this.description, this.by);
        TaskList.add(deadline);
        return Message.COMMAND_ADD.getMessage() + "\t   " + deadline + "\n";
    }
}

package duke.tasklist;

import duke.exception.Messages;
import java.time.LocalDateTime;

/**
 * Represents a deadline task within chat bot.
 * Inherits from task class.
 */
public class Deadline extends Task {
    private final LocalDateTime time;

    /**
     * Constructs deadline object.
     *
     * @param name name of deadline.
     * @param time by datetime of deadline
     */
    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    /**
     * Returns custom string of deadline task.
     * Includes isDone status and deadline task name.
     */
    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(),  Messages.dateFormat(time));
    }

    /**
     * Returns custom string of deadline task for saving.
     */
    public String save() {
        return String.format("D | %s| %s", super.save(),  Messages.dateFormat(time));
    }
}

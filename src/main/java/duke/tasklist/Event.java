package duke.tasklist;

import java.time.LocalDateTime;

import duke.exception.Messages;

/**
 * Represents an event task within chat bot.
 * Inherits from task class.
 */
public class Event extends Task {
    private final LocalDateTime time;

    /**
     * Constructs event object.
     *
     * @param name name of event.
     * @param time at datetime of event
     */
    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    /**
     * Returns custom string of event task.
     * Includes isDone status and event task name.
     *
     * @return task description
     */
    @Override
    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), Messages.dateFormat(time));
    }

    /**
     * Returns custom string of event task for saving.
     *
     * @return task save description
     */
    public String save() {
        return String.format("E | %s| %s", super.save(), Messages.dateFormat(time));
    }
}

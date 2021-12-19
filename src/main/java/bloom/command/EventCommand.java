package bloom.command;

import java.time.LocalDateTime;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Event;

/**
 * Represents an event command which
 * creates an event task with given description
 * with date and time.
 */
public class EventCommand extends Command {

    /** The description of the event. */
    private final String description;

    /** The date and time of the event. */
    private final LocalDateTime at;

    /**
     * Constructor for an EventCommand.
     *
     * @param description the description of the event
     * @param at          the date and time of the event
     */
    public EventCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Creates an event task.
     */
    @Override
    public String run() {
        Event event = new Event(this.description, this.at);
        TaskList.add(event);
        return Message.COMMAND_ADD.getMessage() + "\t   " + event + "\n";
    }
}

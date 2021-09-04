package duke.task;

import duke.exception.InvalidParamException;

/**
 * Represents an Event type Task.
 */
public class Event extends Task {

    /** String that Event uses to distinguish the description from the Event time in the input String */
    private static final String SEPARATOR = " /at ";

    /** String containing the start to end time that this Event will occur on */
    private String at;

    /**
     * Constructs an Event Task with the given description and information.
     *
     * @param description Description of the Event.
     * @param at The specific start to end time that this Event will occur on.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of this Event, which follows the following format:
     * [E][Task status] Task Description (at: Task start time to end time)
     *
     * @return String representation of this Event, which consists of the type of task (Event),
     *         its status, its description, and its start time to end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns an Event Task based on the given description.
     *
     * @param input String containing the Event description.
     * @return Event constructed from the given description.
     * @throws InvalidParamException If the description does not contain the appropriate information or format.
     */
    public static Task setEvent(String input) throws InvalidParamException {
        String[] eventParams = input.split(SEPARATOR);
        if (eventParams.length != 2) {
            throw new InvalidParamException("Please include the time of the event after\n"
                    + "a task description using an '/at' (only once).\n"
                    + "i.e. event project meeting /at Aug 6th 2-4pm");
        }
        return new Event(eventParams[0], eventParams[1]);
    }

    /**
     * Returns the time that the Event will occur on.
     *
     * @return String containing the specific start to end time that this Event will occur on.
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Returns the separator string used to distinguish the description from the Event time in the input String.
     *
     * @return Separator string.
     */
    public static String getSeparator() {
        return SEPARATOR;
    }
}


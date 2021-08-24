package duke.task;

import duke.exception.InvalidParamException;

public class Event extends Task {

    private static final String SEPARATOR = " /at ";

    private String at;

    /**
     * A constructor for this event Task.
     *
     * @param description the description of what the task is.
     * @param at the specific start and end time that this task has to be done at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns an event task based on the given description.
     *
     * @param input the string containing the event task description.
     * @return the event task constructed from the given description.
     * @throws InvalidParamException if the description does not contain the appropriate information.
     */
    public static Task setEvent(String input) throws InvalidParamException {
        String[] eventParams = input.split(SEPARATOR);
        if (eventParams.length != 2) {
            throw new InvalidParamException("Please include the time of the event after\n"
                    + "a task description using an '/at' (only once).\n"
                    + "i.e. event project meeting /at Aug 6th 2-4pm");
        }
        Task event = new Event(eventParams[0], eventParams[1]);
        return event;
    }

    /**
     * Returns the string representation of this Task, which follows the following format:
     * [E][Task status] Task Description (at: Task start time to end time)
     *
     * @return string representation of this Task, which is the type of task (Event),
     *         its status, its description, and its start time to end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String getAt() {
        return this.at;
    }

    public static String getSeparator() {
        return SEPARATOR;
    }
}


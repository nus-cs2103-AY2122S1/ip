package duke.tasks;

import duke.Response;
import duke.Ui;
import duke.exceptions.MissingEventDetailsException;

/**
 * Class that extends from task and represents an event task.
 */
public class Event extends Task {
    protected static final int MAX_SPLIT_LIMIT = 2;
    protected String details;
    protected String taskType;

    /**
     * Public constructor of the event class.
     *
     * @param description The text description of the event.
     * @param details The time or date of the event.
     */
    public Event(String description, String details) {
        super(description);
        this.details = details;
        this.taskType = "E";
    }

    /**
     * Marks the event as done.
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of an event task after the 'event' keyword.
     *
     * @param input The string input provided by the user.
     * @return Description of an event task after the 'event' keyword.
     * @throws MissingEventDetailsException
     */
    public static String getEventDescription(String input) throws MissingEventDetailsException {
        String[] strArr = input.split(" /at", MAX_SPLIT_LIMIT);
        assert strArr.length == MAX_SPLIT_LIMIT: "Missing event description with format \"description /at..\"";
        if (strArr.length < MAX_SPLIT_LIMIT) {
            Ui.showMissingEventDetails();
            Response.showMissingEventDetails();
            throw new MissingEventDetailsException();
        } else {
            return strArr[0] + " (at:" + strArr[1] + ")";
        }
    }

    /**
     * Gets the event details such as date and time.
     *
     * @param input The string input provided by the user.
     * @return Date and/or time of event
     */
    public static String getEventDetails(String input) {
        String[] strArr = input.split("/at", MAX_SPLIT_LIMIT);
        assert strArr.length == 2: "Missing event details with format \"/at..\"";
        return strArr[1];
    }

    /**
     * Returns the string representation of an event.
     *
     * @return String representation of an event.
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString();
    }
}

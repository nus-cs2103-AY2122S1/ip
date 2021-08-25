package duke.tasks;

import duke.Ui;
import duke.exceptions.MissingEventDetailsException;

/**
 * Class that extends from task and represents an event task
 */
public class Event extends Task {
    protected String details;
    protected String taskType;

    /**
     * Public constructor of the event class
     *
     * @param description
     * @param details
     */
    public Event(String description, String details) {
        super(description);
        this.details = details;
        this.taskType = "E";
    }

    /**
     * Gets the task type of an event instance
     *
     * @return E meaning event
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Marks the event as done
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());
    }

    /**
     * Gets the description of an event task after the 'event' keyword
     *
     * @param input
     * @return Description of an event task after the 'event' keyword
     * @throws MissingEventDetailsException
     */
    public static String getEventDescription(String input) throws MissingEventDetailsException {
        String[] strArr = input.split(" /at", 2);
        if (strArr.length < 2) {
            Ui.missingEventDetails();
            throw new MissingEventDetailsException();
        } else {
            return strArr[0] + " (at:" + strArr[1] + ")";
        }
    }

    /**
     * Gets the event details such as date and time
     *
     * @param input
     * @return Date and/or time of event
     */
    public static String getEventDetails(String input) {
        String[] strArr = input.split("/at", 2);
        return strArr[1];
    }

    /**
     * Returns the string representation of an event
     *
     * @return String representation of an event
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString();
    }
}

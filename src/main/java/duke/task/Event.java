package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents a Task with a time which the Task begins.
 */
public class Event extends Task {
    protected LocalDate startTime;

    /**
     * Class constructor that receives a description and the time which the Task begins.
     *
     * @param description Description of the Task.
     * @param startTime Time in which the Task begins.
     */
    public Event(String description, String startTime) {
        super(description);
        this.startTime = LocalDate.parse(startTime);
    }

    /**
     * Method which returns a String representation of the Task to be displayed to the user.
     *
     * @return String Representation of the Task to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at " + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                + " " + super.priorityString();
    }

    /**
     * Method which returns a String representation of the Task to be written to the data file.
     *
     * @return String Representation of the Task to be wrriten to the data file.
     */
    @Override
    public String toFile() {
        return "E " + super.toFile() + "| " + this.startTime;
    }
}

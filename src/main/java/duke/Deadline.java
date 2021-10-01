package duke;

import java.time.LocalDateTime;

/** Child class that extends parent 'Task' class and handles
 * operations for the Deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the task
     * @param by The date which is the deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the date of the object from type LocalDateTime
     * to String for easy manipulation
     *
     * @param dateTime The time of the task stored in list as LocalDateTime
     * @return The manipulated string of the object's date and time
     */
    public String getDateTimeString(LocalDateTime dateTime) {
        String dateTimeString = dateTime.toString();
        String[] splitDateAndTime = dateTimeString.split("T");
        String DateTime = splitDateAndTime[0] + " " + splitDateAndTime[1];
        return DateTime;
    }

    /**
     * toString() method for deadline object
     *
     * @return The task printed in the correct format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "by: " + getDateTimeString(this.by);
    }
}
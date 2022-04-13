package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task that describes an Event, having a description and date of event
 */
public class Event extends Task {
    protected LocalDate dateTime;

    /**
     * Constructs an event with a given description and date & time with uncompleted flag by default
     *
     * @param description Description of the event
     * @param dateTime Date and time of the event
     */
    public Event(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructs an event with a given description, date & time, and completion flag
     *
     * @param description Description of the event
     * @param dateTime Date and time of the event
     * @param isDone Whether the event starts out done
     */
    public Event(String description, LocalDate dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns true if the Task has a due date that is due on the given date.
     *
     * @param dueDate The date to check if this task is on the same deadline/due date
     * @return Returns true if this task is due the same date as the given one
     */
    @Override
    public boolean isDue(LocalDate dueDate) {
        return dueDate.isEqual(this.dateTime);
    }

    /**
     * Returns a formatted version with delimiters of this task for saving to file.
     *
     * @return A formatted String representing the data stored in the task
     */
    @Override
    public String getFormattedData() {
        return super.getFormattedData() + "|" + this.dateTime;
    }

    /**
     * Returns a letter identifying the Task as a Todo.
     *
     * @return A character identifying the Task
     */
    @Override
    public String getTaskIdentifier() {
        return "E";
    }

    /**
     * Returns a string representation of the Todo Task.
     *
     * @return String representing the Todo Task
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] "
                + this.description + " (at: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Does a deep comparison of this object to another object.
     *
     * @param otherObj The other object to be compared to
     * @return Returns true iff the two objects are of same type and same value in every field
     */
    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof Event)) {
            return false;
        } else {
            final Event otherEvent = (Event) otherObj;

            if (this.isDone != otherEvent.isDone) {
                return false;
            } else if (!this.description.equals(otherEvent.description)) {
                return false;
            } else {
                return this.dateTime.equals(otherEvent.dateTime);
            }
        }
    }
}

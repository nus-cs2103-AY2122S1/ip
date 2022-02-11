package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing an Event task.
 */
public class Event extends Task {

    /** the date of the event as a String. */
    private LocalDate atDate;
    /** the time of the event as a String. */
    private LocalTime atTime;

    /**
     * Constructs an Event object using the given task description and timing of the event.
     *
     * @param taskDescription The given task description
     * @param atDate The date of the event as a String
     * @param atTime The time of the event as a String
     */
    public Event (String taskDescription, String atDate, String atTime) {
        super(taskDescription);
        this.atDate = LocalDate.parse(atDate);
        this.atTime = LocalTime.parse(atTime);
    }

    /**
     * Returns a letter as a String that represents the task type.
     *
     * @return The letter "E" that represents an Event.
     */
    public String getTaskType() {
        return "E";
    }

    @Override
    public void setDateAndTime(String date, String time) {
        this.atDate = LocalDate.parse(date);
        this.atTime = LocalTime.parse(time);
    }

    /**
     * Returns the String representation of the task details and timing of the Event.
     * This String will be used to save to the data file.
     *
     * @return The String representation of the task details and timing of the Event.
     */
    @Override
    public String getTaskDescription() {
        return super.getTaskDescription() + " | " + atDate + " | " + atTime;
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + atTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}

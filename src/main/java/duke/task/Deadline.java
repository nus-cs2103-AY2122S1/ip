package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline Task.
 */
public class Deadline extends Task {

    /** The date of the deadline */
    private LocalDate byDate;
    /** The time of the deadline */
    private LocalTime byTime;

    /**
     * Constructs a Deadline task using given description, date and time.
     *
     * @param taskDescription The description of the deadline.
     * @param byDate The date of the deadline.
     * @param byTime The time of the deadline.
     */
    public Deadline (String taskDescription, String byDate, String byTime) {
        super(taskDescription);
        this.byDate = LocalDate.parse(byDate);
        this.byTime = LocalTime.parse(byTime);
    }

    /**
     * Returns a letter as a String that represents the task type.
     *
     * @return The letter "D" that represents a Deadline.
     */
    public String getTaskType() {
        return "D";
    }

    public void setDateAndTime(String date, String time) {
        this.byDate = LocalDate.parse(date);
        this.byTime = LocalTime.parse(time);
    }

    /**
     * Returns the String representation of the task details, date and time of the Deadline.
     * This String will be used to save to the data file.
     *
     * @return The String representation of the task details, date and time.
     */
    @Override
    public String getTaskDescription() {
        return super.getTaskDescription() + " | " + byDate + " | " + byTime;
    }

    /**
     * Returns the String representation of the Deadline which will be used as the output
     * to the user.
     *
     * @return The String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + byTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}

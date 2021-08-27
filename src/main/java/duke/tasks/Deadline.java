package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class used to represent a task that has an end date.
 * Contains method that
 * (i) formats the toString() output such that the result facilitates ease of reading.
 * (ii) overrides the Parent toString method to display the task type,
 * as well as status and description.
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    /**
     * Default constructor for Deadline.
     * @param description String object representing the task being input into Duke.
     * @param date LocalDate object representing the date the Deadline task is due by.
     * @param time LocalTime object representing the time that the Deadline task is due by.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Overloaded constructor for Deadline.
     * @param done String object representing whether the task is completed.
     * @param description String object representing the task being input into Duke.
     * @param date LocalDate object representing the date the Deadline task is due by.
     * @param time LocalTime object representing the time that the Deadline task is due by.
     */
    public Deadline(String done, String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
        if ((done.equals("X"))) {
            this.setIsDone(true);
        } else {
            this.setIsDone(false);
        }
    }

    /**
     * The formatString() method formats the String representation of a Deadline task to facilitate ease of reading.
     *
     * @return String object to represent Deadline task in a more readable manner.
     */
    public String formatString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + this.time.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }

    /**
     * Overriding toString method to display the relevant information
     *
     * @return String type object that includes the task type, parent
     * toString method(), and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}

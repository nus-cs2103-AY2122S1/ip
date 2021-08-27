package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class used to represent a task that has a start and end date.
 * Contains method that
 * (i) formats the toString() output such that the result facilitates ease of reading.
 * (ii) overrides the Parent toString method to display the task type,
 * as well as status and description.
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime start;
    private final LocalTime end;

    /**
     * Default constructor for Event.
     * @param description String object representing the task being input into Duke.
     * @param date LocalDate object representing the date the Event task is on.
     * @param start LocalTime object representing the start time of the Event task.
     * @param end LocalTime object representing the end time of the Event task.
     */
    public Event(String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    /**
     * Overloaded constructor for Event.
     * @param done String object representing whether the task is completed.
     * @param description String object representing the task being input into Duke.
     * @param date LocalDate object representing the date the Event task is on.
     * @param start LocalTime object representing the start time of the Event task.
     * @param end LocalTime object representing the end time of the Event task.
     */
    public Event(String done, String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.date = date;
        this.start = start;
        this.end = end;
        if ((done.equals("X"))) {
            this.setIsDone(true);
        } else {
            this.setIsDone(false);
        }
    }

    /**
     * The formatString() method formats the String representation of an Event task to facilitate ease of reading.
     *
     * @return String object to represent Event task in a more readable manner.
     */
    public String formatString() {
        return "[E]" + super.toString()
                + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.start.format(DateTimeFormatter.ofPattern("h:mma")) + " to "
                + this.end.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }

    /**
     * Overriding toString method to display the relevant information
     *
     * @return String type object that includes the task type, parent
     * toString method(), and timeframe.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + " " + this.start + " " + this.end + ")";
    }
}

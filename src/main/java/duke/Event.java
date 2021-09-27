package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This represents a type of task that has a duration.
 */
public class Event extends Task {

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;

    /**
     * This constructor creates an Event task type object.
     *
     * @param input text description of Event task.
     * @param date date of occurrence of Event.
     * @param startTime start time of Event.
     * @param endTime end time of Event.
     */
    public Event(String input, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(input);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * This method formats task description and duration in a user-friendly way,
     * additionally marking the Event task type with [E].
     *
     * @return String of formatted task description and deadline.
     */
    @Override
    public String toString() {
        String date = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String startTime = this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String endTime = this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return String.format("[E]%s (at: %s from: %s)", super.toString(), date, startTime + "-" + endTime);
    }
}

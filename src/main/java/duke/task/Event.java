package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event, which is a Task with a starting time and an ending time.
 */
public class Event extends Task {
    /** Starting time of the event */
    protected LocalDateTime startingTime;
    /** Ending time of the event */
    protected LocalDateTime endingTime;

    /**
     * Constructor of the class `Event`.
     *
     * @param description Description of the task.
     * @param at Time slot of the task.
     */
    public Event(String description, String at) {
        super(description);
        String[] times = at.split("to", 2);
        this.startingTime = LocalDateTime.parse(times[0].trim(), Task.formatter);
        this.endingTime = LocalDateTime.parse(times[1].trim(), Task.formatter);
        if (this.endingTime.isBefore(this.startingTime)) {
            this.swapTime(); // swap them if they are in incorrect sequence
        }
    }

    /**
     * Swaps the starting time with the ending time.
     */
    private void swapTime() {
        LocalDateTime temporaryTime = this.startingTime;
        this.startingTime = this.endingTime;
        this.endingTime = temporaryTime;
    }

    /**
     * Converts a task with time range to string.
     *
     * @return The string representation of a task with time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                this.startingTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + " to " +
                this.endingTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    /**
     * Converts the task to a string with the format of the file in hard disk.
     *
     * @return String representation of the task in the file's format.
     */
    @Override
    public String toFileFormatString() {
        return String.format("E / %s / %s / %s to %s\n", this.isDone ? "1" : "0", this.description,
                this.startingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.endingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}

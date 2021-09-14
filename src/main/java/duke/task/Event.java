package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDateTime duration;

    /**
     * Constructor for Event
     *
     * @param taskName the name of the input task
     * @param duration the time input for the task
     * @param isDone the current status of the task
     */
    public Event(String taskName, LocalDateTime duration, boolean isDone) {
        super(taskName, isDone);
        this.duration = duration;
    }

    /**
     * Sets the Date and Time for the Event
     *
     * @param duration the input date and time
     */
    public void setDuration(LocalDateTime duration) {
        this.duration = duration;
    }

    /**
     * Prints the task in specified format
     *
     * @return the String that has been formatted
     */
    public String displayTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");
        return "E " + super.displayTask() + "| " + duration.format(formatter);
    }
}

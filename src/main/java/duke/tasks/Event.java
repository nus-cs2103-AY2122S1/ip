package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Event type task
 */
public class Event extends Task {
    private TaskType type = TaskType.EVENT;
    private LocalDateTime timeAt;

    /**
     * Creates a new Event task with description and time of the task
     * @param description - the description of the task
     * @param timeAt - the event time
     */
    public Event (String description, String timeAt){
        super(description);
        this.timeAt = LocalDateTime.parse(timeAt, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Creates a new Event task with description, time of the task and isDone
     * @param description - the description of the task
     * @param timeAt - the event time
     * @param isDone - determines if the task has been completed or not
     */
    public Event (String description, String timeAt, boolean isDone, boolean isHighOrLow) {
        super(description, isDone, isHighOrLow);
        this.timeAt = LocalDateTime.parse(timeAt, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String showTask() {
        return "[E][" + (isDone ? "✗" : " ") + "] " +  "["+ (isHighOrLow ? "High" : "Low") + " ] " + description + " (at: " + timeAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + (isDone ? 1 : 0) + " | " + (isHighOrLow ? 1 : 0) + " | " +  description + " | " + timeAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
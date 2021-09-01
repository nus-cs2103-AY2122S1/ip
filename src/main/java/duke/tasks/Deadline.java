package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Encapsulates a Deadline type task
 */
public class Deadline extends Task {
    private TaskType type = TaskType.DEADLINE;
    private LocalDateTime timeBy;

    /**
     * Creates a new Deadline task with a description and deadline time.
     * @param description - the description of the task
     * @param timeBy - the deadline time for the task
     */
    public Deadline (String description, String timeBy){
        super(description);
        this.timeBy = LocalDateTime.parse(timeBy, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Creates a new Deadline task with a description, deadline time and isDone
     * @param description - the description of the task
     * @param timeBy - the deadline time for the task
     * @param isDone - determines if the task has been completed or not
     */
    public Deadline (String description, String timeBy, boolean isDone) {
        super(description, isDone);
        this.timeBy = LocalDateTime.parse(timeBy, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String showTask(){
        return "[D][" + (isDone ? "✗" : " ") + "] " + description + " (by: " + timeBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    @Override
    public String saveTask() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + timeBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

}

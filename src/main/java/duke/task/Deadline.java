package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime due;

    /**
     * Constructor for Deadline
     *
     * @param taskName the name of the task input
     * @param due the time input for the task
     * @param status the current status for the task
     */
    public Deadline(String taskName, LocalDateTime due, boolean status) {
        super(taskName, status);
        this.due = due;
    }

    /**
     * Prints the task in specified format
     *
     * @return the String that has been formatted
     */
    public String displayTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");
        return "D " + super.displayTask() + "| " + due.format(formatter);
    }
}

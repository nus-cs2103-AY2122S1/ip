package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task with a given deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime dateAndTime;

    /**
     * Initialises a new instance of Deadline.
     *
     * @param taskDescription The description of the deadline.
     * @param dateAndTime The deadline time and date.
     * @param isDone Marks the deadline as done or not done.
     */
    public Deadline(String taskDescription, LocalDateTime dateAndTime, boolean isDone) {
        super(taskDescription, isDone);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns the String representation of the Deadline task.
     *
     * @return The String representation of the task.
     */
    @Override
    public String toString() {
        String dateAndTime = DateTimeFormatter.ofPattern("dd MMM yyyy 'at' h:mm a")
                .format(this.dateAndTime);
        if (super.isDone) {
            return "[D][X] " + super.taskDescription + " (by: " + dateAndTime + ")";
        } else {
            return "[D][ ] " + super.taskDescription + " (by: " + dateAndTime + ")";
        }
    }

    /**
     * Returns a String representation of the task to store within the save file.
     *
     * @return The String representation of the task.
     */
    @Override
    public String parseToString() {
        String result = super.isDone ? "X" : "0";
        result += "D/" + super.taskDescription + "/" + this.dateAndTime.toString();
        return result;
    }
}

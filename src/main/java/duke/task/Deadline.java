package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Parser;
import duke.exception.DukeException;

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
     Returns a new instance of Deadline with updated values.
     *
     * @param taskDescription The description of the updated Deadline.
     * @param dateAndTime The date and time of the updated Deadline.
     * @return A Deadline with the updated description or time, or both.
     * @throws DukeException If the given date and time is invalid.
     */
    public Deadline update(String taskDescription, String dateAndTime) throws DukeException {
        try {
            String updatedDescription = taskDescription == null
                    ? this.taskDescription
                    : taskDescription;
            LocalDateTime updatedTime = dateAndTime == null
                    ? this.dateAndTime
                    : Parser.formatDateTime(dateAndTime);
            return new Deadline(updatedDescription, updatedTime, this.isDone);

        } catch (DukeException de) {
            throw new DukeException(de.getMessage());
        }
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

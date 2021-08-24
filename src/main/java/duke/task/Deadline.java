package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dateAndTime;

    public Deadline(String taskName, LocalDateTime dateAndTime, boolean isDone) {
        super(taskName, isDone);
        this.dateAndTime = dateAndTime;
    }

    /**
<<<<<<< HEAD
     * Returns the String representation of the Deadline task.
     *
     * @return The String representation of the task.
=======
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation of the task.
>>>>>>> Level-9
     */
    @Override
    public String toString() {
        String dateAndTime = DateTimeFormatter.ofPattern("dd MMM yyyy 'at' h:mm a")
                .format(this.dateAndTime);
        if (super.isDone) {
            return "[D][X] " + super.taskName + " (by: " + dateAndTime + ")";
        } else {
            return "[D][ ] " + super.taskName + " (by: " + dateAndTime + ")";
        }
    }

    /**
<<<<<<< HEAD
     * Returns a String representation of the task to store within the save file.
     *
     * @return The String representation of the task.
=======
     * Returns a string representation of the task to store within the save file.
     *
     * @return The string representation of the task.
>>>>>>> Level-9
     */
    @Override
    public String parseToString() {
        String result = super.isDone ? "X" : "0";
        result += "D/" + super.taskName + "/" + this.dateAndTime.toString();
        return result;
    }
}

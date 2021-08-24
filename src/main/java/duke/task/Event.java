package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime dateAndTime;
    private final LocalTime endTime;

    public Event(String taskName, LocalDateTime dateAndTime, LocalTime endTime, boolean isDone) {
        super(taskName, isDone);
        this.dateAndTime = dateAndTime;
        this.endTime = endTime;
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
        String dateAndTime = DateTimeFormatter.ofPattern("dd MMM yyyy 'from' h:mm a")
                .format(this.dateAndTime);
        String endTime = DateTimeFormatter.ofPattern("h:mm a").format(this.endTime);
        if (super.isDone) {
            return "[E][X] " + super.taskName + " (at: " + dateAndTime + " - " + endTime + ")";
        } else {
            return "[E][ ] " + super.taskName + " (at: " + dateAndTime + " - " + endTime + ")";
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
        result += "E/" + super.taskName + "/" + this.dateAndTime.toString() + "/"
                + this.endTime.toString();
        return result;
    }
}

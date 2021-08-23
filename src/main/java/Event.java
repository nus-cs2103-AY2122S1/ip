import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private final LocalDateTime dateAndTime;

    public Event(String taskName, LocalDateTime dateAndTime, boolean isDone) {
        super(taskName, isDone);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns the string representation of the Deadline task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String dateAndTime = DateTimeFormatter.ofPattern("dd MMM yyyy 'at' h:mm a")
                .format(this.dateAndTime);
        if (super.isDone) {
            return "[E][X] " + super.taskName + " (at: " + dateAndTime + ")";
        } else {
            return "[E][ ] " + super.taskName + " (at: " + dateAndTime + ")";
        }
    }

    /**
     * Returns a string representation of the task to store within the save file.
     * @return A string representation of the task.
     */
    @Override
    public String parseToString() {
        String result = super.isDone ? "X" : "0";
        result += "E/" + super.taskName + "/" + this.dateAndTime.toString();
        return result;
    }
}

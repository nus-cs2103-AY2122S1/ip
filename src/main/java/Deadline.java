import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// class that handles Deadline tasks 
// -> Deadline is a type of Task with a do by date/time
public class Deadline extends Task {
    private LocalDateTime dueDate;

    // Constructor for a Deadline
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    // String representation of a Deadline
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        return "[D]["
            + ((this.isCompleted()) ? "X] " : " ] ")
            + this.getDescription()
            + " (by: " 
            + this.dueDate.format(format) 
            + ")";
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends TimeTask {

    public Deadline(String description) {
        super(description);
    }
    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
    }

    public Deadline(String description, String by) {
        super(description, by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.getTime().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

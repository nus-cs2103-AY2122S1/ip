import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends DateDependentTask {
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    public Deadline(String description, LocalDate by, boolean isCompleted) {
        super(description, by, isCompleted);
    }

    protected String getShortForm() {
        return "D";
    }

    public String getDateString() {
        return super.getDateString();
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + super.getDateString() + ")";
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate deadline;
    protected String logo = "[E]";

    public Event(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String logo() {
        return logo;
    }

    private String dateFormat() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() +  " " + super.description + " (at: " + dateFormat() + " " + ")";
    }
}
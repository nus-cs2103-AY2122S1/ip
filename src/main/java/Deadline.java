import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate deadline;
    protected String logo = "[D]";
    protected String time;

    public Deadline(String description, LocalDate deadline, String time) {
        super(description);
        this.deadline = deadline;
        this.time = time;
    }

    public String logo() {
        return logo;
    }

    private String dateFormat() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() +  " " + super.description + " (by: " + dateFormat() + " " + time +")";
    }
}

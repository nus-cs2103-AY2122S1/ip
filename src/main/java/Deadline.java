import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDate date;

    public Deadline(String label, LocalDate date) {
        this.date = date;
        this.label = label;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

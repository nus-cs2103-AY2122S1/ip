import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate date;
    private final LocalTime time;

    public Deadline(String taskName, LocalDate date, LocalTime time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    @Override
    public boolean hasSameDate(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ", " + formattedTime + ")";
    }
}

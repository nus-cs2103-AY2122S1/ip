import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected String[] segments;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.segments = by.split(" ");
    }

    @Override
    public String getDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateFormatterOutput = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(this.segments[0], dateFormatter);
        return date.format(dateFormatterOutput).toString();
    }

    @Override
    public String getTime() {
        if (this.segments.length > 1) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("k:mm");
            DateTimeFormatter timeFormatterOutput = DateTimeFormatter.ofPattern("h.mma");
            LocalTime time = LocalTime.parse(this.segments[1], timeFormatter);
            return " " + time.format(timeFormatterOutput).toString();
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + this.getTime() + ")";
    }
}
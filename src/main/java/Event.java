import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected String[] segments;
    protected String[] times;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.segments = at.split(" ");
        if (this.segments.length > 1) {
            this.times = this.segments[1].split("-");
        }
    }

    @Override
    public String getDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateFormatterOutput = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(segments[0], dateFormatter);
        return date.format(dateFormatterOutput).toString();
    }

    @Override
    public String getTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("k:mm");
        DateTimeFormatter timeFormatterOutput = DateTimeFormatter.ofPattern("h.mma");
        LocalTime timeStart = LocalTime.parse(times[0], timeFormatter);
        LocalTime timeEnd = LocalTime.parse(times[1], timeFormatter);
        return timeStart.format(timeFormatterOutput).toString() + " to " +
                timeEnd.format(timeFormatterOutput).toString();

    }

    @Override
    public String getReadableString() {
        String status = this.isDone ? "1" : "0";
        return "E | " + status + " | " + this.description + " | " + this.at  + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDate() + " " + this.getTime() + ")";
    }
}
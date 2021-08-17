import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate date;

    public Event(String label) {
        String[] arr = label.split("/", 2);
        date = LocalDate.parse(arr[1].substring(3));
        this.label = arr[0];
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")";
    }
}

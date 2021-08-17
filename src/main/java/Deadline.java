import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String label) {
        String[] arr = label.split("/", 2);
        date = LocalDate.parse(arr[1].substring(3));
        this.label = arr[0];
    }

    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")";
    }
}

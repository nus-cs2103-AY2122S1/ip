package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected String timeToDisplay;
    protected LocalDateTime deadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        int date = Integer.parseInt(by.substring(0, 2));
        int month = Integer.parseInt(by.substring(3, 5));
        int year = Integer.parseInt(by.substring(6, 10));
        int hour = Integer.parseInt(by.substring(11, 13));
        int min = Integer.parseInt(by.substring(14, 16));

        LocalDateTime taskDate = LocalDateTime.of(year, month, date, hour, min);
        this.timeToDisplay = taskDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        deadline = taskDate;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + timeToDisplay + ")";
    }

    @Override
    public String toPrintToFile() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

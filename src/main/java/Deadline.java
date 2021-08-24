import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected LocalTime time;
    protected LocalDate date;

    public Deadline(String description, LocalTime time, LocalDate date) {
        super(description);
        this.time = time;
        this.date = date;
    }

    @Override
    public String outputFormat() {
        return "D" + super.outputFormat() + " | " + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear()
                + " " + String.format("%1$" + 2 + "s", time.getHour()).replace(' ', '0') + String.format("%1$" + 2 + "s", time.getMinute()).replace(' ', '0');
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                + ", " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
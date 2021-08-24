import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    private LocalDate date;
    private LocalTime time;

    public Deadline(String description, String deadline) {
        super(description, "[D]");
        if (deadline.equals("")) {
            throw new MissingDateException();
        } else {
            this.date = LocalDate.parse(deadline.substring(0,10));
            this.time = LocalTime.parse(deadline.substring(11), DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    public Deadline(String description, String date, String time) {
        super(description, "[D]");
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("h:ma"));
    }

    @Override
    public String getDescription() {
        return super.getDescription()
                + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + this.time.format(DateTimeFormatter.ofPattern(" h:ma"))
                + ")";
    }
}

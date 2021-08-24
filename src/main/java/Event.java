import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String dateNum;
    protected LocalDate date;

    public Event(String description, String date) {
        super(description);
        this.dateNum = date;
        this.date = LocalDate.parse(date);
    }

    @Override
    public String getTask() {
        return "E";
    }

    public String getDate() {
        return "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getDateNum() {
        return dateNum;
    }
}

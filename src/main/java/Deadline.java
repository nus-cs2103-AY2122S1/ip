import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String dateNum;
    protected LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        this.dateNum = date;
            this.date = LocalDate.parse(date);
    }

    @Override
    public String getTask() {
        return "D"; // mark done task with X
    }

    public String getDate() {
        return "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getDateNum() {
        return dateNum;
    }
}

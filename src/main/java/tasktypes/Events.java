package tasktypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected String date;
    protected LocalDate at;

    public Events(String description, String eventDate) {
        super(description, "E");
        this.date = eventDate;
    }

    public String understandDate() {
        at = LocalDate.parse(this.date);
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String hardDiskSave() {
        return "E/" + this.getBooleanStatus() + "/" + this.getDescription() + "/" + this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + understandDate() + ")";
    }
}
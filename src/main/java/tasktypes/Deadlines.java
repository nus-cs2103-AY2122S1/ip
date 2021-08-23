package tasktypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected String by;
    protected LocalDate date;

    public Deadlines(String description, String finishBy) {
        super(description, "D");
        this.by = finishBy;
    }

    public String understandDate() {
        date = LocalDate.parse(this.by);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String hardDiskSave() {
        return "D/" + this.getBooleanStatus() + "/" + this.getDescription() + "/" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + understandDate() + ")";
    }
}
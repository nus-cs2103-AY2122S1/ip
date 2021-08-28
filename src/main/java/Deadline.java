import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected String type;
    private LocalDate completionDate;

    public Deadline(String information, String by,String type) {
        super(information);
        this.completionDate = LocalDate.parse(by);
        this.type = type;
        this.by = by;
    }

    public String getType() {
        return type;
    }

    public String getDetails() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + completionDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
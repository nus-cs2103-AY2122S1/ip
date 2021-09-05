import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    protected String stringDueBy;
    protected LocalDate dateDueBy;

//    public Deadline(String description, String by) {
//        super(description);
//        this.by = by;
//    }

//    public Deadline(String description, LocalDate dateDueBy) {
//        super(description);
//        this.dateDueBy = dateDueBy;
//        this.by = dateDueBy.format(RFC_1123_DATE_TIME);
//    }

    public Deadline(String description, LocalDate dateDueBy) {
        super(description);
        this.stringDueBy = dateDueBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.dateDueBy = dateDueBy;
    }

    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + stringDueBy + ")";
    }
}

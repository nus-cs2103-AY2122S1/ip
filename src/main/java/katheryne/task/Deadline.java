package katheryne.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String stringDueBy;
    protected LocalDate dateDueBy;
    
    // dummy constructor for Jackson
    public Deadline() {
        super();
    }
    public Deadline(String description, LocalDate dateDueBy) {
        super(description);
        this.stringDueBy = dateDueBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.dateDueBy = dateDueBy;
    }
    
    // getters & setters (needed for jackson)
    protected void setDateDueBy(LocalDate dateDueBy) {
        this.dateDueBy = dateDueBy;
    }

    protected void setStringDueBy(String stringDueBy) {
        this.stringDueBy = stringDueBy;
    }
    
    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + stringDueBy + ")";
    }
}

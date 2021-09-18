package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {

    private String by;
    private LocalDateTime byAsDate;
    private String dateAsString;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byAsDate = Parser.textToDate(by);
        dateAsString = Parser.dateToText(this.byAsDate);
    }

    @Override
    public String toString() {
        if (this.getTag() != null) {
            return "[D]" + super.toString() + " (by: " + dateAsString + ")" + " " + this.getTag();
        } else {
            return "[D]" + super.toString() + " (by: " + dateAsString + ")";
        }
    }

    @Override
    public String toStringForStorage() {
        if (this.getTag() != null) {
            return "deadline " + super.toStringForStorage() + " | " + by + "/ " + super.isDone + " " + this.getTag();
        } else {
            return "deadline " + super.toStringForStorage() + " | " + by + "/ " + super.isDone;
        }
    }
}

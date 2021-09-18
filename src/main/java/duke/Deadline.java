package duke;

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
        return "[D]" + super.toString() + " (by: " + dateAsString + ")";
    }

    @Override
    public String toStringForStorage() {
        return "deadline " + super.toStringForStorage() + " | " + by + "/ " + super.isDone;
    }
}

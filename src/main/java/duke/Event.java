package duke;

import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime atAsDate;
    private String at;
    private String dateAsString;


    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.atAsDate = Parser.textToDate(at);
        dateAsString = Parser.dateToText(this.atAsDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAsString + ")";
    }

    @Override
    public String toStringForStorage() {
        return "event " + super.toStringForStorage() + " | " + at + "/ " + super.isDone;
    }
}

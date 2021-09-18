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
        if (this.getTag() != null) {
            return "[E]" + super.toString() + " (at: " + dateAsString + ")" + " " + this.getTag();
        } else {
            return "[E]" + super.toString() + " (at: " + dateAsString + ")";
        }
    }

    @Override
    public String toStringForStorage() {
        if (this.getTag() != null) {
            return "event " + super.toStringForStorage() + " | " + at + "/ " + super.isDone + " " + this.getTag();
        } else {
            return "event " + super.toStringForStorage() + " | " + at + "/ " + super.isDone;
        }
    }
}

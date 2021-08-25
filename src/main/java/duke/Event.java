package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String when;
    private LocalDate time = null;

    public Event(String str1, String str2) throws DukeException {
        super(str1);
        this.when = str2;
    }

    public Event(String str1, LocalDate time) throws DukeException {
        super(str1);
        this.time = time;
    }

    @Override
    public String toString() {
        if (time == null) {
            return "[E]" + super.toString() + " (at: " + when + ")";
        } else {
            return "[E]" + super.toString() + " (at: "+ time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
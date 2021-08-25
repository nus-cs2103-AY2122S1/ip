package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * class that extends task
 * labelled with an extra [E] when called
 * stores the date which task should occur
 */
public class Event extends Task {
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        if (at.contains("/")) {
            date = LocalDate.parse(at.split("/")[2] + "-" + at.split("/")[1] + "-" +
                    (Integer.parseInt(at.split("/")[0]) < 10 ? "0" + at.split("/")[0] :
                            at.split("/")[0]));
        } else {
            date = LocalDate.parse(at);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

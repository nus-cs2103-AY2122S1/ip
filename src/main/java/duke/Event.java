package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event Task that occurs at a specified time
 */
public class Event extends Task {
    protected LocalDateTime at;

    public Event(String name, LocalDateTime at, Boolean isDone) {
        super(name,'E', isDone);
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    public static Task parseCommand(String str) throws TaskException {
        String[] detailE = str.split(" /at ", 2);
        if (detailE.length == 1) {
            throw new TaskException("When is the event?");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime atTime = LocalDateTime.parse(detailE[1], formatter1);
        Event newE = new Event(detailE[0], atTime, false);
        return newE;
    }

    @Override
    public String getSavedAs() {
        return (super.getSavedAs() + "|" + this.at);
    }
}
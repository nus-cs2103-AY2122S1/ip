package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline task that needs to be done by a specified time
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String name, LocalDateTime by, Boolean isDone) {
        super(name, 'D', isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }

    public static Task parseCommand(String str) throws TaskException {
        String[] detailD = str.split(" /by ", 2);
        if (detailD.length == 1) {
            throw new TaskException("When is the deadline?");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime byTime = LocalDateTime.parse(detailD[1], formatter1);
        Deadline newD = new Deadline(detailD[0], byTime, false);
        return newD;

    }
    @Override
    public String toSavedAs() {
        return (super.toSavedAs() + "|" + this.by);
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String when;
    private LocalDate time = null;

    public Deadline(String str1, String str2) throws DukeException {
        super(str1);
        this.when = str2;
    }

    public Deadline(String str1, LocalDate time) throws DukeException {
        super(str1);
        this.time = time;
    }

    @Override
    public String toString() {
        if (time == null) {
            return "[D]" + super.toString() + " (by: " + when + ")";
        } else {
            return "[D]" + super.toString() + " (by: " +
                    time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
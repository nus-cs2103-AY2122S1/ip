package duke;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String name) throws DukeDeadlineException, DateTimeException {
        super(name.substring(0, name.indexOf(" /by ") + 1));
            this.deadline = LocalDate.parse(name.substring(name.indexOf(" /by ") + 5));
        if (name.equals("")) {
            throw  new DukeDeadlineException();
        }
    }

    public Deadline(String[] input) {
        super(input[2].substring(0, input[1].length() - 1), input[1].equals("T") ? true : false);
        this.deadline = LocalDate.parse(input[3]);
    }

    public String getDate() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDataString() {
        return "D|" + super.toDataString() + "|" + this.deadline;
    }
}

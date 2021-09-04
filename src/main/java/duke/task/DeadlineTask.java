package duke.task;

import duke.error.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    protected LocalDate time;

    public DeadlineTask(String description, String time) throws DukeException {
        super(description);
        this.type = "D";
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!! wrong date format.\nthe format should be:\n\tyyyy-mm-dd");
        }
    }

    public DeadlineTask(String description, boolean isDone, String time) {
        super(description, isDone);
        this.type = "D";
        this.time = LocalDate.parse(time);
    }

    public String getTime() {
        return time.toString();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}

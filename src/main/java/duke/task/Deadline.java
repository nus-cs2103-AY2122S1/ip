package duke.task;
import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String deadline;
    protected String date = "";

    public Deadline(String description, String deadline) throws DukeException {
        super(description);

        try {
            LocalDate localDate = LocalDate.parse(deadline);
            this.date = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("duke.task.Deadline should be in a yyyy-mm-dd format.");
        }

        if (description.isEmpty() || description == "" || description == " ") {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            this.description = description;
        }

        if (deadline.isEmpty() || deadline == "" || deadline == " ") {
            throw new DukeException("☹ OOPS!!! The deadline of this task must be indicated.");
        } else {
            this.deadline = this.date;
        }
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String addOns() {
        return this.deadline;
    }
}

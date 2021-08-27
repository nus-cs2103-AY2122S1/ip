package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected LocalDate deadline;
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    private String formatDate() {
        return deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + formatDate() + ")";
    }
}

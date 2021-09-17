package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeadlineTask extends Task {

    private LocalDate deadline;

    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskStatus() {
        return "[D]" + super.getTaskStatus();
    }

    @Override
    public String toString() {
        return super.toString() + " " + "(by: " + this.deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ")";
    }
}

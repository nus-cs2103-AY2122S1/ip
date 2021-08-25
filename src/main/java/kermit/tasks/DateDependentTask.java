package kermit.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DateDependentTask extends Task {
    LocalDate date;

    public DateDependentTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public DateDependentTask(String description, LocalDate date, boolean isCompleted) {
        super(description, isCompleted);
        this.date = date;
    }

    public String getDate() {
        return this.date.toString();
    }

    protected String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

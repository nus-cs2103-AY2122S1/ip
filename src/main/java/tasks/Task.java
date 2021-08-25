package tasks;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static LocalDate formatDate(String dateInput) {
        // assume that the acceptable format for dates related to deadline and event are in the form of
        // "year-month-day"
        LocalDate ld = LocalDate.parse(dateInput);

        return ld;
    }

    @Override
    public String toString() {
        String res = "[" + getStatus() + "] " + description;

        return res;
    }
}
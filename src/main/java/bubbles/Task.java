package bubbles;

import java.time.LocalDate;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String format() {
        return this.toString();
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
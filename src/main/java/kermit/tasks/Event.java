package kermit.tasks;

import java.time.LocalDate;

public class Event extends DateDependentTask {
    public Event(String description, LocalDate at) {
        super(description, at);
    }

    public Event(String description, LocalDate at, boolean isCompleted) {
        super(description, at, isCompleted);
    }

    public String getShortForm() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + super.getFormattedDateString() + ")";
    }
}
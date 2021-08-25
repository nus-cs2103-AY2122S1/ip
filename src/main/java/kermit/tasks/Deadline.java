package kermit.tasks;

import java.time.LocalDate;

public class Deadline extends DateDependentTask {
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    public Deadline(String description, LocalDate by, boolean isCompleted) {
        super(description, by, isCompleted);
    }

    public String getShortForm() {
        return "D";
    }

    public String getDateString() {
        return super.getDateString();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + super.getDateString() + ")";
    }
}

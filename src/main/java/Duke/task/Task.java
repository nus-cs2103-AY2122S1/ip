package duke.task;

import java.time.LocalDate;

abstract public class Task {

    private String details;
    private boolean completed;

    public Task(String details) {
        this.details = details;
        completed = false;
    }

    // Completes task
    public void isComplete() {
        completed = true;
    }

    // Check if task is completed
    public boolean isCompleted() {
        return completed;
    }

    public String getDetails() {
        return details;
    }

    abstract public LocalDate getDate();
    abstract public int getTime();

    @Override
    public String toString() {
        String status = completed
                ? "[X]"
                : "[ ]";
        return status
                + " "
                + details;
    }
}

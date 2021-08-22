package duke.task;

import java.time.LocalDate;

abstract public class Task {

    private String details;
    private boolean completed;

    public Task(String details) {
        this.details = details;
        this.completed = false;
    }

    // Completes task
    public void complete() {
        this.completed = true;
    }

    // Check if task is completed
    public boolean isCompleted() {
        return this.completed;
    }

    public String getDetails() {
        return this.details;
    }

    abstract public LocalDate getDate();
    abstract public int getTime();

    @Override
    public String toString() {
        String status = this.completed ? "[X]" : "[ ]";
        return status + " " + details;
    }
}

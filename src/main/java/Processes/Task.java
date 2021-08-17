package Processes;

public class Task {

    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}
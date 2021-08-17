package Processes;

public class Task {

    protected String description;
    protected boolean done;
    protected String remarks;

    public Task(String description, String remarks) {
        this.description = description;
        this.done = false;
        this.remarks = remarks;
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
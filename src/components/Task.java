package components;

public class Task {
    protected String taskDescription;
    protected boolean done;

    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.done = false;
    }

    /*
        Return true if the task done value actually changes.
     */
    public boolean markDone() {
        if (this.done == true) {
            return false;
        }
        this.done = true;
        return true;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[ ] [X] %s", this.taskDescription);
        } else {
            return String.format("[ ] [ ] %s", this.taskDescription);
        }
    }
}

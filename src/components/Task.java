package components;

abstract public class Task {
    protected String taskDescription;
    protected boolean done;

    protected Task(String taskDescription, boolean done) {
        this.taskDescription = taskDescription;
        this.done = done;
    }

    abstract public Task markDone();

}

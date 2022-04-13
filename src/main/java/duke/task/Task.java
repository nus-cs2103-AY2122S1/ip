package duke.task;


/**
 * <code>Task</code> class which is the parent class of To-Do, Deadline and Event
 * A basic task consists of a description and a boolean to indicate if the task is done.
 */

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() { return this.description; }

    public void setDone() {
        this.isDone = true;
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

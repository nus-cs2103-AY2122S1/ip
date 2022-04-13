package duke;

/**
 * A subclass of Task that can be stored inside the tasklist
 */
public class ToDoTask extends Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public ToDoTask(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "T";
    }
    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}

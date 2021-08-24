package duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + description;
    }

    @Override
    public String getStatusString() { return "T@" + (isDone ? 1 : 0) + "@" + this.description; }
}

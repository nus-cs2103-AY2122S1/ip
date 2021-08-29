package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public boolean getIsDone() { return this.isDone;}

    public String getDescription() { return this.description; }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
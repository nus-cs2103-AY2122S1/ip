package duke.task;

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
        return "[T]" + (this.isDone ? "[X] " : "[ ] ") + this.description;
    }

    @Override
    public String toStorage() {
        return ("T%" + this.isDone + "%" + this.description + "\n");
    }
}

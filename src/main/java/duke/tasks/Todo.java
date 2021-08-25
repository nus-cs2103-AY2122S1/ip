package duke.tasks;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    public Todo markAsDone() {
        return new Todo(this.description, true);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

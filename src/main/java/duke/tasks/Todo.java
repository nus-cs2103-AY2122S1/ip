package duke.tasks;

public class Todo extends Task {

    public Todo(String description, boolean done) {
        super(description, Task.Type.TODO, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package duke.task;

public class Todo extends Task {

    public Todo(String todoName) {
        super(todoName.substring(5));
    }

    public Todo(String todoName, boolean isDone) {
        super(todoName, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

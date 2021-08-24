package duke.tasks;

/** Class representing a todo */
public class Todo extends Task {
    public Todo(String taskDetails) {
        super(taskDetails);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toDataString() {
        return String.format("T | %s", super.toDataString());
    }
}

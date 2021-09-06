package duke.task;

public class Todo extends Task {

    public Todo(String description, boolean done) { super(description, done); }

    @Override
    public String toFileData() {
        return String.join(Task.STORAGE_DELIMITER, Task.TODO_ALPHABET, super.toFileData());
    }

    @Override
    public String toString() {
        return super.wrapTaskAlphabet(Task.TODO_ALPHABET) + super.toString();
    }
}

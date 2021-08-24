package duke.task;

public class Todo extends Task {
    private static final char TASK_LETTER = 'T';

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s", Todo.TASK_LETTER, super.toString());
    }

    @Override
    public String stringToStore() {
        return Todo.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + "\n";
    }
}

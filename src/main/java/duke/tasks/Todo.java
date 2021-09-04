package duke.tasks;

public class Todo extends Task {
    private static final String IDENTIFIER = "T";

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String getIdentifier() {
        return IDENTIFIER;
    }

    public String getDetailsWithDelimiter(String delimiter) {
        return taskName;
    }
}

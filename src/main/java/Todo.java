public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String getIdentifier() {
        return "T";
    }

    public String getDetailsWithDelimiter(String delimiter) {
        return taskName;
    }
}

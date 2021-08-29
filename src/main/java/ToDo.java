public class ToDo extends Task {
    public ToDo(TaskType type, String description) {
        super(type, description);
    }

    public ToDo(TaskType type, String description, boolean isDone) {
        super(type, description, isDone);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TODO.getAbbr() + "] " + super.toString();
    }
}

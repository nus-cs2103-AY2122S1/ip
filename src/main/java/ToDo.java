public class ToDo extends Task {
    public ToDo(TaskType type, String description) {
        super(type, description);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TODO.getAbbr() + "]" + super.toString();
    }
}

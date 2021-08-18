public class ToDo extends Task{
    public ToDo(String taskName, boolean isDone) {
        super(taskName, "ToDo", false);
    }

    public ToDo(String taskName) {
        this(taskName, false);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

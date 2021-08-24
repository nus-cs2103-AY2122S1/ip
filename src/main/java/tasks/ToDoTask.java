package tasks;

public class ToDoTask extends Task {
    public static final String KEYWORD = "[TODO]";
    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return KEYWORD + " " + super.toString();
    }
}

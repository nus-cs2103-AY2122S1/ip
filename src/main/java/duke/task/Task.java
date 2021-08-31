package duke.task;

public class Task {

    public static final String DONE_MARKER = "X";
    public static final String NOT_DONE_MARKER = " ";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean b) {
        isDone = b;
    }

    @Override
    public String toString() {
        String marker;
        if (isDone) {
            marker = DONE_MARKER;
        } else {
            marker = NOT_DONE_MARKER;
        }
        return String.format("[%s] %s", marker, description);
    }
}
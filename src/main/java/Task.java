public class Task {

    private String description;
    private int index;
    private boolean isDone;

    public Task(String description, int index) {
        this.description = description;
        this.index = index;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return index + "."
                + "[" + (isDone ? "X" : "") + "] "
                + description;
    }

    public String showAsDone() {
        return "[X] " + description;
    }
}

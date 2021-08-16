public class Task {
    private final String title;
    private boolean isDone;

    public Task(String title) {
        this(title, false);
    }

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public String toString() {
        String string = "[";
        if (isDone)
            string += "X";
        else
            string += " ";
        string += "] " + title;
        return string;
    }
}

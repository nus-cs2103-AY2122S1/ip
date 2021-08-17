public class Task {
    private boolean isDone = false;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String checkBox = isDone ? "🗵 " : "☐ ";
        return checkBox + description;
    }
}

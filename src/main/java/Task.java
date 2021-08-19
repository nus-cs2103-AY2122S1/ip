public abstract class Task {
    private String description;
    private boolean isDone;

    public static Task create(String params) {
        return null;
    }

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    private String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }
}

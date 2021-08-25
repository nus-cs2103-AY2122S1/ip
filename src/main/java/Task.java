public abstract class Task {
    public final static String STORAGE_DELIMITER = "/";

    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
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

    public String formatForStorage() {
        return description + STORAGE_DELIMITER + isDone;
    }
}

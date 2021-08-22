public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatus() {
        return (isDone ? "true" : "false");
    }

    public String save() {
        return String.format("%s:%s:%s:\n", this.getType(), this.getStatus(), this.description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public static boolean isValid(String[] arr) throws DukeException {
        return true;
    }

    public String getType() {
        return " ";
    }

    public void markDone() {
      this.isDone = true;
    }
}

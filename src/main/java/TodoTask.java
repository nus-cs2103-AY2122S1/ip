public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
        this.type = "T";
    }

    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
        this.type = "T";
    }
}

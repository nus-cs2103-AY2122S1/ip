public class Task {
    private static final String DONE_MSG = "Well done.";

    private boolean isDone;
    private String description;
    private String status;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.status = "[ ]";
    }

    public String markAsDone() {
        this.isDone = true;
        this.status = "[X]";
        return DONE_MSG + "\n" + this.toString();
    }

    public String actionString() {
        return this.status + " " + this.description;
    }

    @Override
    public String toString() {
        return this.status + " " + this.description;
    }
}



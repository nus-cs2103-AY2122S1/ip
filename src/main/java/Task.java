public class Task {
    private static final String DONE_MSG = "Well done.";
    private static final String NEW_TASK_MSG = "New task added:";
    private static final String DELETE_MSG = "The following have been deleted:";

    private String type;
    private String description;
    private boolean isDone;
    private String status;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.status = "[ ]";
        this.type = type;
    }

    public String markAsDone() {
        this.isDone = true;
        this.status = "[X]";
        return DONE_MSG + "\n" + this.toString();
    }

    public String delete() {
        return DELETE_MSG + "\n" + this.toString();
    }

    public String actionString() {
        return NEW_TASK_MSG + "\n"
            + this.toString();
    }

    @Override
    public String toString() {
        return this.type + this.status + " " + this.description;
    }
}



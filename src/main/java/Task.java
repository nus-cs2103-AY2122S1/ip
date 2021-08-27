public abstract class Task {
    private static final String DONE_MSG = "Well done.";
    private static final String NEW_TASK_MSG = "New task added:";
    private static final String DELETE_MSG = "The following have been deleted:";

    private String type;
    private String description;
    private boolean isDone;
    private String statusSymbol;

    public Task(String description, String type, boolean status) {
        this.description = description;
        this.isDone = status;
        this.statusSymbol = status ? "[X]" : "[ ]";
        this.type = type;
    }

    public String markAsDone() {
        this.isDone = true;
        this.statusSymbol = "[X]";
        return DONE_MSG + "\n" + this.toString();
    }

    public String delete() {
        return DELETE_MSG + "\n" + this.toString();
    }

    public String commandString() {
        return NEW_TASK_MSG + "\n"
            + this.toString();
    }

    public abstract String getformmatedData();

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.type + this.statusSymbol + " " + this.description;
    }
}



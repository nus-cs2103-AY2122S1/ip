package tasks;

public abstract class Task {

    private static final String DONE_MSG = "Well done.";

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

    /**
     * Marks task as done
     */
    public String markAsDone() {
        this.isDone = true;
        this.statusSymbol = "[X]";
        return DONE_MSG + "\n" + this.toString();
    }

    /**
     * Returns the data of the event separated be "|"
     */
    public abstract String getFormattedData();

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



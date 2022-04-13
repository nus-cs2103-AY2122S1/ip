package duke;

public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        assert name != null : "Item name should not be null";
        assert name != "" : "Item name should not be empty";

        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }

    /**
     * Returns the Task item as a string for storage.
     * @return the string for storage.
     */
    public String toSaveString() {
        return this.name + "~" + (isDone ? "1" : "0");
    }

    /**
     * Sets the done status.
     * @param done the new done status.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isFound(String target) {
        return this.name.contains(target);
    }
}
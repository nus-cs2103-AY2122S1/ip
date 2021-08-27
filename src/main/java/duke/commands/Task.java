package duke.commands;

/**
 * Encapsulates a Task that has a description and a completion state isDone
 *
 * @author Owen Tan
 * @version Duke Level-9
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void completeTask() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public boolean findKeyword(String keyword) {
        return description.contains(keyword);
    }

    public abstract String printFormat();
}

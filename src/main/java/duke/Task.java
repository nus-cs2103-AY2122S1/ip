package duke;

public abstract class Task {
    private final String description;
    private Boolean completionStatus;

    public Task(String description, Boolean completionStatus) {
        this.description = description;
        this.completionStatus = completionStatus;
    }

    public void completeTask() {
        completionStatus = true;
    }

    public String completionIcon() {
        return completionStatus ? "[X]" : "[-]";
    }

    public abstract String typeIcon();

    @Override
    public String toString() {
        return typeIcon() + " " + completionIcon() + " " + description;
    }
}

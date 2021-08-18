public class Task {
    private final String description;
    private Boolean completionStatus;

    public Task(String description) {
        this.description = description;
        this.completionStatus = false;
    }

    public void completeTask() {
        completionStatus = true;
    }

    @Override
    public String toString() {
        if (completionStatus) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}

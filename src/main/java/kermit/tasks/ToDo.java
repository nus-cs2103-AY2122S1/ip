package kermit.tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    public String getShortForm() {
        return "T";
    }
}

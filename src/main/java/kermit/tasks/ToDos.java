package kermit.tasks;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    public String getShortForm() {
        return "T";
    }
}

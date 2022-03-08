package model;

public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String getTaskStatus() {
        return "[T]" + super.getTaskStatus();
    }

    @Override
    public String getTaskStatusForStorage() {
        return "T " + super.getTaskStatusForStorage();
    }
}

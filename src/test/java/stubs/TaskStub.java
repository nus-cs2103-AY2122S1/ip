package stubs;

import petal.task.Task;

public class TaskStub extends Task {

    private boolean isDone;

    public TaskStub(String description, boolean isDone) {
        super(description, isDone);
    }

    public TaskStub() {
        super("run", false);
    }

    @Override
    public String formatStrForSaving() {
        return "This task was saved!";
    }

    @Override
    public boolean isTimeable() {
        return false;
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + "Go for a run";
    }

}

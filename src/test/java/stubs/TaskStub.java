package stubs;

import petal.task.Task;

import java.time.LocalDate;

public class TaskStub extends Task {

    private boolean isDone;

    public TaskStub(String description, boolean isDone, LocalDate localDate) {
        super(description, isDone, localDate);
    }

    public TaskStub() {
        super("run", false);
    }

    @Override
    public String strForSaving() {
        return "This task was saved!";
    }

    @Override
    public boolean isTimeable() {
        return false;
    }

    @Override
    public void taskDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "run";
    }

}
package duke.task;

abstract public class Task {
    private String name;
    private boolean isCompleted = false;

    public Task(String name) {
        this.name = name;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    abstract public String logo();

    public String getName() {
        return name;
    }
}

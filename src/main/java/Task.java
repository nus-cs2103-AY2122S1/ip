public abstract class Task {
    protected int id;
    protected String description;
    protected boolean isDone;

    public enum TaskTypes {
        TODO, DEADLINE, EVENT
    }

    public static int numOfTasks = 0;

    public Task(String description) {
        this.id = numOfTasks;
        numOfTasks++;
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String checkbox = isDone ? "[X]" : "[ ]";
        return checkbox + " " + description;
    }
}

package duke.task;

abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    public String getDesc() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: \n" + this.toString());
    }

    public boolean checkIfDone() {
        return this.isDone;
    }

    public String getType() {
        return "";
    }

    public String addOns() {
        return "";
    }
}

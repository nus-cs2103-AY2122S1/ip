public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public void addTaskMsg(int size) {
        System.out.println(
                "I have added the task!\n  "
                + this
                + "\nNow you have " + size + " tasks left!");
    }


}

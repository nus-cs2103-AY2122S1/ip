public class Task {
    protected String description;
    protected boolean isDone;
    protected Integer order;

    public Task(String description) throws DukeException1 {
        if(description.equals("blah")) {
            throw new DukeException1();
        }
        this.description = description;
        this.isDone = false;
        this.order = 0;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

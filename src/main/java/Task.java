public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Integer order;

    public Task(String description) {
        /*if(description.equals("blah")) {
            throw new DukeException1();
        }*/
        this.description = description;
        this.isDone = false;
        this.order = 0;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getInfo();

    public abstract String getType();

    public String getDone() {
        return (isDone ? "1" : "0");
    };

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        isDone = true;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String storeTask();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

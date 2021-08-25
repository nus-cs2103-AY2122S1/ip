package duke;

public abstract class Task {
    protected static final String COMPLETED = "[X]";
    protected static final String NOT_COMPLETED = "[ ]";

    protected final String description;
    protected boolean isDone;

    public Task(String str) {
        this.description = str;
        this.isDone = false;
    }

    protected void markDone() {
        isDone = true;
    }
    protected abstract String getTaskType();


    protected String getDate() throws DukeNoDateException{
        throw new DukeNoDateException();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            // Casting is valid as the instance of the object is explicitly checked
            return description.equals(((Task) obj).description);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return (isDone ? COMPLETED : NOT_COMPLETED) + " " + description;
    }
}

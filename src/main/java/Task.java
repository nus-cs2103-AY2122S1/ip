public class Task {
    private static final String COMPLETED = "[X]";
    private static final String NOT_COMPLETED = "[]";

    protected final String description;
    protected boolean isDone;

    public Task(String str) {
        this.description = str;
        this.isDone = false;
    }

    protected void markDone() {
        isDone = true;
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

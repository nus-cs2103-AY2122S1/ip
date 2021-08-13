public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("empty description");
        }
        this.description = description;
        this.isDone = false;
    }

    private String getStatus() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    public void markDone() {
        this.isDone = true;
    }
}
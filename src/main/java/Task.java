public class Task {
    protected int id;
    protected String description;
    protected boolean isDone;
    protected static int idCounter = 1;

    public Task(String description) {
        this.id = idCounter;
        this.description = description;
        this.isDone = false;
        idCounter++;
    }

    public int getId() {
        return id;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}

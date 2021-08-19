public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String fullDescription = String.format("[%s] %s", getStatusIcon(), getDescription());
        return fullDescription;
    }


}

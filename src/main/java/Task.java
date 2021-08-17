public class Task {

    private String description;

    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.setDone(true);
    }

    @Override
    public String toString() {
        StringBuilder taskStringBuilder = new StringBuilder();

        if (done) {
            taskStringBuilder.append("[x] ");
        } else {
            taskStringBuilder.append("[ ] ");
        }
        taskStringBuilder.append(action);

        return taskStringBuilder.toString();
    }
}

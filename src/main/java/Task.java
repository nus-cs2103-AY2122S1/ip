public class Task {

    private String action;

    private boolean done;

    public Task(String action, boolean done) {
        this.action = action;
        this.done = done;
    }

    public Task(String action) {
        this.action = action;
        this.done = false;
    }

    public String getAction() {
        return action;
    }

    public boolean isDone() {
        return done;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setCompleted() {
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

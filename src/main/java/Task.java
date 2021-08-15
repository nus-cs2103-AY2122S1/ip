public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * produces a graphical way of whether a task is done or not.
     *
     * @return a graphical way of whether a task is done or not
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Mark a task as done and leaves a marked task unchanged.
     *
     * @return a String that confirms the operation of marking task is done
     */
    public String markAsDone() {
        if(isDone) {
            return "Task is already marked as done";
        }
        isDone = true;
        return "Nice! I've marked this task as done:\n" + " " + toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStatusIcon()).append(" ").append(this.description);
        return sb.toString();
    }
}

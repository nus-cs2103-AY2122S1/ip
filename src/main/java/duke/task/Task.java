package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status of the task (whether is it done)
     * @return Status Icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Get description of the task
     * @return decription of task
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Mark task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String fullDescription = String.format("[%s] %s", getStatusIcon(), getDescription());
        return fullDescription;
    }

    public String toFileString() {
        String fileString = String.format("%s | %s", getStatusIcon(), getDescription());
        return fileString;
    }

    public boolean isKeyWordPresent(String keyWord) {
        int intIndex = description.indexOf(keyWord);

        if (intIndex == -1) {
            return false;
        } else {
            return true;
        }
    }

}

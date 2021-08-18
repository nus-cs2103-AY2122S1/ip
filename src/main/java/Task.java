public abstract class Task {

    protected String taskTitle;
    protected boolean isDone;

    /**
     * Constructor
     * @param taskTitle
     */
    public Task (String taskTitle) {
        this.taskTitle = taskTitle;
        this.isDone = false;
    }

    /**
     * Sets isDone to true
     */
    public void maskAsDone() {
        this.isDone = true;
    }
}

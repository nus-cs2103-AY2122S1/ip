public class Task {
    private String task;
    private Boolean isDone;
    private String statusIcon = " ";

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Sets task to done.
     */
    public void setDone() {
        this.isDone = true;
        this.statusIcon = "X";
    }

    /**
     * Check if task is done.
     * @return True if task is done and False otherwise.
     */
    public Boolean checkStatus() {
        return isDone;
    }

    /**
     * Checks task status and returns String representation of the task and its status.
     * @return String of the task and its status.
     */
    @Override
    public String toString(){
        return String.format("[%s] %s", this.statusIcon, this.task);
    }
}

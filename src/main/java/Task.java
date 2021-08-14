public class Task {
    private String task;
    private Boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Checks task status and returns String representation of the task and its status.
     * @return String of the task and its status.
     */
    public String getTask(){
        if(this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

    /**
     * Sets task to done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Check if task is done.
     * @return True if task is done and False otherwise.
     */
    public Boolean checkStatus() {
        return isDone;
    }
}

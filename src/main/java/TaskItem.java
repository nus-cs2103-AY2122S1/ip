public class TaskItem {

    private String task;
    private boolean completed;

    /**
     * Constructor for creating a TaskItem object.
     * @param task the description of the TaskItem.
     */
    public TaskItem(String task) {
        this.task = task;
        this.completed = false; // by default if you add it to the list then it should be false first.
    }

    /**
     * describes the task.
     * @return returns the description of the TaskItem as a String.
     */
    public String describeTaskItem() {
        return this.task;
    }

    /**
     * queries whether the task is completed or not.
     * @return a boolean to tell whether or not the task is completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Completes this task.
     */
    public void completeTask() {
        this.completed = true;
    }

    /**
     * Overriden toString method.
     * @return a String representation of a TaskItem.
     */
    @Override
    public String toString() {
        if (completed) {
            return "[X]" + this.task;
        } else {
            return "[ ]" + this.task;
        }
    }
}

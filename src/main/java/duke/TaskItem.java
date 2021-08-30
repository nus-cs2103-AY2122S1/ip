package duke;

public class TaskItem {

    private String task;
    private boolean completed;

    /**
     * Constructor for creating a duke.TaskItem object.
     * @param task the description of the duke.TaskItem.
     */
    public TaskItem(String task) {
        this.task = task;
        this.completed = false; // by default if you add it to the list then it should be false first.
    }

    /**
     * describes the task.
     * @return returns the description of the duke.TaskItem as a String.
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
     * toFileString returns the TaskItem in a string.
     * @return returns the Deadline in a string format that is specifically made for the dukeData.txt file.
     */
    public String toFileString() {
        if (completed) {
            return "[X]" + this.task;
        } else {
            return "[ ]" + this.task;
        }
    }

    /**
     * Overriden toString method.
     * @return a String representation of a duke.TaskItem.
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

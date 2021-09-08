package duke.task;

/**
 * class to represent tasks
 *
 */
public abstract class Task {

    protected String title;
    protected Boolean isDone;

    Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    Task(String title, Boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /** function to mark this task as done */
    public void markAsDone() {
        this.isDone = true;
    }

    abstract void updateDate(String date);

    /**
     * Returns string representation of the task for displaying
     *
     * @return string representation of the task for displaying
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.title;
        } else {
            return "[ ] " + this.title;
        }
    }

    /**
     * Returns a string representation of the task for saving
     *
     * @return string representation of the task for saving
     */
    public String saveString() {
        if (this.isDone) {
            return "Task : 1 : " + this.title;
        } else {
            return "Task : 0 : " + this.title;
        }
    }

    public String getTitle() {
        return this.title;
    }
}

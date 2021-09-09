package whobot.task;

/***
 * Parent Class for Tasks
 */
public class Task implements Comparable<Task> {

    /** Description of the Task */
    private final String description;

    /** Whether Task is Done */
    private boolean isDone;

    private String tag;

    /***
     * Constructor for Task Class
     *
     * @param task the string description of the Task
     */
    public Task(String task) {
        this.description = task;
        this.tag = "";
        this.isDone = false;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public boolean hasTag() {
        return !tag.isBlank();
    }

    /***
     * Marks the Task as Done
     */
    public void markAsDone() {
        isDone = true;
    }

    /***
     * Marks the Task as Not Done
     */
    public void markAsUndone() {
        isDone = false;
    }

    /***
     * Returns the status of the task as boolean
     *
     * @return isDone
     */
    public boolean getDone() {
        return isDone;
    }

    /***
     * Returns the status of the task as a string
     *
     * @return "[X]" if done, else "[ ]"
     */
    public String getStatusIcon() {
        return getDone() ? "[X]" : "[ ]";
    }

    /***
     * Returns the String description of the task (overridden by subclasses)
     *
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /***
     * Returns the String description of the task
     *
     * @return description
     */
    public String getTask() {
        return description;
    }

    /***
     * Returns the type of the task (overridden by subclasses)
     *
     * @return null since this is parent class
     */
    public String getType() {
        return null;
    }

    /***
     * Returns string representation of the task
     *
     * @return string to display for the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /***
     * Compares this task to another, to help with sorting
     *
     * @param o Task to compare to
     * @return -1 if other task is done and this isn't, and 1 for vice versa. 0 if both same
     */
    @Override
    public int compareTo(Task o) {
        if (!this.isDone && o.isDone) {
            return -1;
        } else if (this.isDone && !o.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    /***
     * Equates this task to another
     *
     * @param o Task to equate to
     * @return true if both have the same description and are done
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone && description.equals(task.description);
    }
}

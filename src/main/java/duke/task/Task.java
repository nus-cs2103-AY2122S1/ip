package duke.task;
/**
 * Task class to represent the tasks in the list.
 * It supports (i) getting status icon
 * and (ii) marking a task as done
 *
 * @author Benjamin Lui
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class,
     * initially the task is not done.
     * @param description the name of the task
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Constructor for the Task class.
     * @param description the name of the task
     * @param isDone whether the task is done, based on its status icon
     */
    public Task(String description, String isDone) {
        this.description = description.trim();
        if (isDone.equals("X")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Returns the status icon of the Task.
     * @return the status icon based on whether the task is completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the status of the current task to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String base = "[" + getStatusIcon() + "] " + this.description;
        return base;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            Task task = (Task) object;
            return task.getClass() == this.getClass()
                    && task.description.equals(this.description);
        }
        return false;
    }
}

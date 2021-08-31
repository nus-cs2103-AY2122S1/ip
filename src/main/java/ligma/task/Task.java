package ligma.task;

/**
 * This class represents a task.
 */
public class Task {
    public enum TaskType {
        TODO, EVENT, DEADLINE
    }
    private String desc;
    private boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param desc                      the description of the task
     * @return                          task created
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Creates a Task object.
     *
     * @param desc                      the description of the task
     * @param isDone                    whether task has been completed
     * @return                          task created
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Marks this task as done.
     *
     * @return this task object
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Returns task description of this task.
     *
     * @return task description
     */
    public String getTaskDesc() {
        return desc;
    }

    /**
     * Determine if task contains target string.
     *
     * @param target string to search for in task description
     * @return true if it's a match, false otherwise
     */
    public boolean match(String target) {
        return desc.contains(target);
    }

    @Override
    public String toString() {
        String check = isDone ? "[X] " : "[ ] ";
        return check + desc;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return desc.equals(((Task) obj).desc);
        }
        return false;
    }
}

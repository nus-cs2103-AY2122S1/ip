package ligma.task;

/**
 * This class represents a task.
 */
public class Task {
    public enum TaskType {
        TODO, EVENT, DEADLINE, RECURRING
    }
    private String desc;
    private String meta;
    private boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param desc                      the description of the task
     * @param meta                      meta-info about task
     * @return                          task created
     */
    public Task(String desc, String meta) {
        assert(desc.trim() != "") : "Task description is empty";
        this.desc = desc;
        this.meta = meta;
        this.isDone = false;
    }

    /**
     * Creates a Task object.
     *
     * @param desc                      the description of the task
     * @param isDone                    whether task has been completed
     * @return                          task created
     */
    public Task(String desc, boolean isDone, String meta) {
        assert(desc.trim() != "") : "Task description is empty";
        this.desc = desc;
        this.meta = meta;
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
     * Determines if task contains target string.
     *
     * @param target string to search for in task description
     * @return true if it's a match, false otherwise
     */
    public boolean match(String target) {
        return desc.contains(target);
    }

    /**
     * Returns meta details about task for storage purposes.
     *
     * @return meta details for task creation upon reload
     */
    public String getFullMeta() {
        return " " + isDone + " " + meta;
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

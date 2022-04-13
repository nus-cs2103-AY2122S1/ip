package duke.assignment;

/**
 * Enumeration to indicate the type of task.
 */
public enum AssignmentType {
    /**
     * TODO marks a to do task input by user.
     * EVENT marks a to do task input by user.
     * DEADLINE marks a to do task input by user.
     */
    TODO("[T]"), EVENT("[E]"), DEADLINE("[D]");

    /**
     * Stores the string format of each task.
     * of [T], [E], [D]
     */
    private final String type;

    AssignmentType(String type) {
        this.type = type;
    }

    /**
     * returns the task type in their corresponding label format.
     * @return String of the Task type
     */
    public String getStatus() {
        return this.type;
    }
}

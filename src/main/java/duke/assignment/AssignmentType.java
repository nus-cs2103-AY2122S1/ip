package duke.assignment;

/**
 * Enumeration to indicate the type of task
 */
public enum AssignmentType {

    TODO("[T]"), EVENT("[E]"), DEADLINE("[D]");
    private final String type;

    AssignmentType(String type) {
        this.type = type;
    }

    /**
     * returns the task type in their corresponding label format
     * 
     * @return String of the Task type
     */
    public String getStatus() {
        return this.type;
    }
}

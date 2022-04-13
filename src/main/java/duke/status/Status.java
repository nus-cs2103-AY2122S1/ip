package duke.status;

/**
 * Enumeration to indicate if
 * task is completed or not.
 */
public enum Status {

    /**
     * Marks a task as completed or not.
     */
    COMPLETED("[X]"), NOT_COMPLETED("[]");

    /**
     * Status of a task where
     * completed is [X] and uncompleted
     * is [].
     */
    private final String status;

    Status(String status) {
        this.status = status;
    }

    /**
     * returns the status of task in their
     * corresponding label format.
     * @return String of the status
     */
    public String getStatus() {
        return this.status;
    }

}
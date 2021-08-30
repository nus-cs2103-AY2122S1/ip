package duke.status;

/**
 * Enumeration to indicate if task is completed or not
 */
public enum Status {
    COMPLETED("[X]"), NOT_COMPLETED("[]");
    private final String status;

    Status(String status) {
        this.status = status;
    }

    /**
     * returns the status of task in their corresponding label format
     * 
     * @return String of the status
     */
    public String getStatus() {
        return this.status;
    }

}
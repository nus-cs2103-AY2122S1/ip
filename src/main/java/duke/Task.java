package duke;

/**
 * Class includes methods required for creating a task
 * and obtaining information relating to it.
 */
public class Task {
    private String information;
    private Boolean isCompletedTask;
    private String type;

    /**
     * Creates a task.
     *
     * @param information refers to details of task
     */
    public Task(String information) {
        this.information = information;
        this.isCompletedTask = false;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isCompletedTask = true;
    }

    /**
     * Returns information relevant to the task.
     *
     * @return task information
     */
    public String getInformation() {
        return information;
    }

    /**
     * Returns type of task.
     *
     * @return task type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns details relevant to the task.
     *
     * @return task details
     */
    public String getDetails() {
        return null;
    }

    /**
     * Returns task in a string format.
     *
     * @return task formatted as a string
     */
    public String toString() {
        return getStatusIcon() + this.information;
    }

    /**
     * Returns status of task pertaining to whether it has been completed.
     *
     * @return task status
     */
    public String getStatusIcon() {
        if (isCompletedTask) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    /**
     * Returns boolean of whether string can be found in the task information.
     *
     * @param searchString words we are searching for in task information
     * @return boolean depending on whether task consists of searchString
     */
    public boolean isContainingSimilarInformation(String searchString) {
        return this.information.contains(searchString);
    }

}

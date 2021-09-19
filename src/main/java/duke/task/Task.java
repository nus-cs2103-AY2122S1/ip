package duke.task;

/**
 * Task class used by bot.
 *
 * @author mrmrinal
 */
public class Task {

    static final String SEPARATOR = " | ";
    protected String description;
    protected boolean isDone;

    /**
    * Constructor of task
    *
    * @param description Description of task.
    */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
    * Constructor of task
    *
    * @param description Description of task.
    * @param done Status of Task
    */
    public Task(String description, int done) {
        this.description = description;
        this.isDone = done == 1;
    }

    /**
     * Get description of task.
     *
     * @return String description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return status of task as string.
     *
     * @return String status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Change task status to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method to return String that has been stored in the Arraylist.
     *
     * @return String stored in the ArrayList
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }


    /**
     * Method to return String that has been stored in the txt File.
     *
     * @return String stored in the .txt file
     */
    public String toStorageString() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        String taskDisplay = "T";
        String newLine = "\n";
        return taskDisplay + SEPARATOR + done + SEPARATOR + this.description + newLine;
    }
}

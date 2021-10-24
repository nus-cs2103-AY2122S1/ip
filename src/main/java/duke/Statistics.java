package duke;

/**
 * Deals with the management of statistics such as,
 * total tasks done, total tasks added, and total tasks deleted.
 */
public class Statistics {

    int totalTasksDone;
    int totalTasksAdded;
    int totalTasksDeleted;
    
    public Statistics() {
        this.totalTasksDone = 0;
        this.totalTasksAdded = 0;
        this.totalTasksDeleted = 0;
    }
    
    public Statistics(int done, int added, int deleted) {
        this.totalTasksDone = done;
        this.totalTasksAdded = added;
        this.totalTasksDeleted = deleted;
    }

    /**
     * Increments the totalTasksDone statistic by 1.
     */
    public void incrementTaskDone() {
        this.totalTasksDone++;
    }

    /**
     * Increments the totalTasksAdded statistic by 1.
     */
    public void incrementTaskAdded() {
        this.totalTasksAdded++;
    }

    /**
     * Increments the totalTasksDeleted statistic by 1.
     */
    public void incrementTaskDeleted() {
        this.totalTasksDeleted++;
    }

    /**
     * Returns the totalTasksDone statistic.
     * 
     * @return
     */
    public String getTasksDone() {
        return "" + this.totalTasksDone;
    }

    /**
     * Returns the totalTasksAdded statistic.
     *
     * @return
     */
    public String getTasksAdded() {
        return "" + this.totalTasksAdded;
    }

    /**
     * Returns the totalTasksDeleted statistic.
     *
     * @return
     */
    public String getTasksDeleted() {
        return "" + this.totalTasksDeleted;
    }
    
    
    @Override
    public String toString() {
        String returnMessage = "";
        returnMessage += "Total tasks done: " + this.totalTasksDone + "\n";
        returnMessage += "Total tasks added: " + this.totalTasksAdded + "\n";
        returnMessage += "Total tasks deleted: " + this.totalTasksDeleted;
        return returnMessage;
    }
}

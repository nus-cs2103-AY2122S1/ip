package duke;

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
    
    public void incrementTaskDone() {
        this.totalTasksDone++;
    }

    public void incrementTaskAdded() {
        this.totalTasksAdded++;
    }

    public void incrementTaskDeleted() {
        this.totalTasksDeleted++;
    }
    
    public String getTasksDone() {
        return "" + this.totalTasksDone;
    }
    
    public String getTasksAdded() {
        return "" + this.totalTasksAdded;
    }
    
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

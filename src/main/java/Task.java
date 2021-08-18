import java.util.ArrayList;

public class Task {

    private boolean completedStatus = false;
    private String name;
    private int taskNumber;
    private static int numberOfTask = 0;


    public Task(String name) {
        this.name = name;
        numberOfTask += 1;
        this.taskNumber = numberOfTask;
    }

    public void completeTask() {
        this.completedStatus = true;
    }

    public String toString() {
        String status;
        if (completedStatus) {
            status = "[✓] ";
        } else {
            status = "[X] ";
        }
       return status + name;
    }

    public boolean getCompletedStatus() {
        return this.completedStatus;
    }

    public static int getNumberOfTask() {
        return numberOfTask;
    }
}

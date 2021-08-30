package duke;

public class Task {
    protected String information;
    protected Boolean isCompletedTask;
    protected String type;

    public Task(String information) {
        this.information = information;
        this.isCompletedTask = false;
    }

    public void markDone() {
        this.isCompletedTask = true;
    }
    public String getInformation() {
        return information;
    }
    public String getType(){
        return this.type;
    }
    public String getDetails() {
        return null;
    }
    public String toString() {
        return getStatusIcon() + this.information;
    }
    public String getStatusIcon() {
        if (isCompletedTask) {
            return "[X] ";
        } else {
            return  "[ ] ";
        }
    }
}
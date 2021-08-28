public class Task {
    protected String information;
    protected Boolean completedTask;
    protected String type;

    public Task(String information) {
        this.information = information;
        this.completedTask = false;
    }

    public void isDone() {
        this.completedTask = true;
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
        if (completedTask) {
            return "[X] ";
        } else {
            return  "[ ] ";
        }
    }
}
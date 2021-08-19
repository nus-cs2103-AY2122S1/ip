public class Task {
    protected String information;
    protected Boolean completedTask;

    public Task(String information) {
        this.information = information;
        this.completedTask = false;
    }
    public void done() {
        this.completedTask = true;
    }

    public String getStatusIcon() {
        if (completedTask) {
            return "X";
        } else {
            return  " ";
        }
    }
}
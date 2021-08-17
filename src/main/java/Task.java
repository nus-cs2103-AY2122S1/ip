public class Task {
    protected boolean complete;
    protected String task;


    public Task(String task) {
        this.task = task;
        this.complete = false;
    }

    String printTask() {
        String result = "";
        if (complete) {
            result = "[X] ";
        } else {
            result = "[ ] ";
        }
        return result + this.task;
    }

    void setComplete() {
        this.complete = true;
    }
}

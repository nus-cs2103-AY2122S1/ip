public class Task {
    static private int totalList = 1;

    private String task;
    private boolean complete = false;

    public Task(String task) {
        this.task = task;
        totalList++;
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

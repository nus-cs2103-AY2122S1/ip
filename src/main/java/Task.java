public class Task {
    private String message;
    private boolean done;

    public Task(String message) {
        this.message = message;
        this.done = false;
    }

    public String getDone() {
        return this.done ? "X" : " ";
    }

    public String getMessage() {
        return this.message;
    }

    public void completeTask() {
        this.done = true;
    }

}

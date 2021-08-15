public class Task {
    private String taskTitle;
    private boolean isDone;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        //When a task is first created, it is by default not completed.
        this.isDone = false;
    }

    @Override
    public String toString() {
        if(isDone) {
            return "[X] " + taskTitle;
        } else {
            return "[ ] " + taskTitle;
        }
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }
}

public class Task {

    protected String taskTitle;
    protected boolean isDone;

    public Task (String taskTitle) {
        this.taskTitle = taskTitle;
        this.isDone = false;
    }

    public void maskAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString(){
        return String.format("[%c] %s", (this.isDone ? 'X' : ' '), this.taskTitle);
    }

}

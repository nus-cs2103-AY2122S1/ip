public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void taskDone() {
        System.out.println(Duke.indentation + "\nYou have completed the task: " + "'"
                                            + this.description + "'!"
                                            + "\nI am so happy for you!\n"
                                             + Duke.indentation);
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}

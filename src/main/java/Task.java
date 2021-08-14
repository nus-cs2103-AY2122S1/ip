public class Task {

    protected String description;
    protected boolean isDone;
    private final String indentation = "-----------------------------------------------\n";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void taskDone() {
        System.out.println(indentation + "You have completed the task: " + "'"
                                       + this.description + "'!"
                                       + "\nI am so happy for you!"
                                       + indentation);
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}

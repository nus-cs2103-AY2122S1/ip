public class Task {
    private String taskName;
    private boolean state;

    public Task(String taskName) {
        this.taskName = taskName;
        this.state = false;
    }

    public void printTask() {
        System.out.println(state ? "[X] " : "[ ] " +  this.taskName);
    }

    public void doneTask() {
        this.state = true;
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       [X] " + taskName);
    }

    @Override
    public String toString() {
        return (state ? "[X] " : "[ ] ") +  this.taskName;
    }
}

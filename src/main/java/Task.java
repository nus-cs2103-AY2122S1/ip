public class Task {
    private final int index;
    private final String taskName;
    private boolean done;

    public Task(int index, String taskName) {
        this.index = index;
        this.taskName = taskName;
        this.done = false;
    }

    public void setDone() {
        this.done = true;
        System.out.print("Nice! I've marked this task as done:\n  [X] " + this.taskName+ "\n");
    }

    public String listEntry() {
        if (done) {
            return ".[X] " + this.taskName;
        } else {
            return ".[ ] " + this.taskName;
        }
    }
}

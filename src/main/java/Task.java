public class Task {
    private boolean done;
    private String taskString;

    public Task(String taskString) {
        this.taskString = taskString;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public String getTaskString() {
        if(done) {
            return "[X] " + this.taskString;
        } else {
            return "[ ] " + this.taskString;
        }
    }
}

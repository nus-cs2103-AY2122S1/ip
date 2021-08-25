package duke;

public class Deadline extends Task {
    private boolean done;
    private String taskString;
    private String deadline;

    public Deadline(String taskString, String deadline) {
        this.taskString = taskString;
        this.done = false;
        this.deadline = deadline;
    }

    public void markAsDone() {
        this.done = true;
    }

    public String getTaskString() {
        if(done) {
            return "[D][X] " + this.taskString + " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.taskString + " (by: " + this.deadline + ")";
        }
    }

    public boolean getDone() {
        return done;
    }

    public String getDescription() {
        return taskString;
    }

    public String getDeadline() {
        return deadline;
    }
}

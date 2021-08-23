public class Deadline extends Task{
    public String deadlineTime;

    public Deadline(String task, String deadlineTime) {
        super(task);
        this.deadlineTime = deadlineTime;
    }

    public Deadline(String task, boolean done, String deadlineTime) {
        super(task, done);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.done) {
            finished = "X";
        }
        return "[D]" + "[" + finished + "] " + this.taskName + " (by: " + this.deadlineTime + ")";
    }

    @Override
    public String toStoredString() {
        int finished = done ? 1 : 0;
        return "D | " + finished + " | " + taskName + " | " + deadlineTime;
    }
}

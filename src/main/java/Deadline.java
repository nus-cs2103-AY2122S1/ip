public class Deadline extends Task{
    private String time;

    public Deadline(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName() + this.getTimeString();
    }

    public String getTimeString() {
        return "(by: " + this.time + ")";
    }
}

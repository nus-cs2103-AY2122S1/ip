public class Deadline extends Task {
    private String dateOrTime;

    public Deadline(String taskName, String dateOrTime) {
        super(taskName);
        this.dateOrTime = dateOrTime;
    }

    @Override
    public String toString() {
        if (super.isDone) {
            return "[D][X] " + super.taskName + " (by: " + this.dateOrTime + ")";
        } else {
            return "[D][ ] " + super.taskName + " (by: " + this.dateOrTime + ")";
        }
    }
}

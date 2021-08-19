public class Deadline extends Task{

    private String time;
    taskType type;
    public Deadline(String taskName, String time) {
        super(taskName);
        this.time = time;
        type = taskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + this.time + ")";
    }
}

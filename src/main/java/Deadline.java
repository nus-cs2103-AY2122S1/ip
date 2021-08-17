public class Deadline extends Task{

    private String due;

    Deadline(String taskName, String due) {
        super(taskName);
        this.due = due;
    }

    public String displayTask() {
        return "[D]" + super.displayTask() + " (by: " + due + ")";
    }
}

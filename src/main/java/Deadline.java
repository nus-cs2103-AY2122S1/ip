public class Deadline extends Task{

    private String due;

    Deadline(String taskName, String due, boolean status) {
        super(taskName, status);
        this.due = due;
    }

    public String displayTask() {
        return "D " + super.displayTask() + "| " + due;
    }
}

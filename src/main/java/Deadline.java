public class Deadline extends Task{
    private String by;

    public Deadline (String taskDetails, String by) {
        super(taskDetails);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
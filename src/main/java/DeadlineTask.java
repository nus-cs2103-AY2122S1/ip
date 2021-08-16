public class DeadlineTask extends Task {
    protected String time;

    public DeadlineTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
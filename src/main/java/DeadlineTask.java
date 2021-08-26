public class DeadlineTask extends Task {
    protected String time;

    public DeadlineTask(String description, String time) {
        super(description);
        this.type = "D";
        this.time = time;
    }

    public DeadlineTask(String description, boolean isDone, String time) {
        super(description, isDone);
        this.type = "D";
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.time + ")";
    }

}

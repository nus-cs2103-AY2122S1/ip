public class Deadlines extends Task {

    protected String deadlineTime;

    public Deadlines(String description, String time) {
        super(description);
        deadlineTime = time;
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] " + this.description + "(by: %s)",
            this.getStatusIcon(), this.deadlineTime);
    }

    @Override
    public String toDataFileString() {
        return String.format("D|%s|%s|%s", this.isDone ? "1" : "0", this.description, this.deadlineTime);
    }
}

public class Deadline extends Task {
    private String time;

    public Deadline(String desc, String time) {
        super(desc);
        this.time = time;
    }

    public String getDeadline() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.time + ")";
    }
}

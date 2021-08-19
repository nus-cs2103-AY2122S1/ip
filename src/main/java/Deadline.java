public class Deadline extends Task {

    private String endTime;

    public Deadline(String str, String endTime) {
        super(str);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }
}

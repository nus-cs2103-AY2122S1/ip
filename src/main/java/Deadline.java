public class Deadline extends Task {

    String dueDate;

    Deadline(String desc, String time) {
        super(desc);
        this.dueDate = time;
    }

    @Override
    public String toString() {
        return "[D]" + this.completionStatus() + desc + " (by: " + dueDate + ")";
    }
}

public class Deadline extends Task {

    String dueDate;

    Deadline(String desc, Boolean isDone, String time) {
        super(desc, isDone);
        this.dueDate = time;
    }

    @Override
    public String toString() {
        return "[D]" + this.completionStatus() + desc + " (by: " + dueDate + ")";
    }
}

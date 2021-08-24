public class Deadline extends Task {

    String dueDate;

    Deadline(String desc, Boolean isDone, String time) {
        super(desc, isDone);
        this.dueDate = time;
    }

    @Override
    public String saveText() {
        int isDone = this.isDone ? 1 : 0;
        return "D | " + isDone + " | " + desc + " | " + dueDate + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + this.completionStatus() + desc + " (by: " + dueDate + ")";
    }
}

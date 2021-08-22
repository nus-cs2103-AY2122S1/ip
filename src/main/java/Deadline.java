public class Deadline extends Task {
    private final Datetime dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = new Datetime(dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate.toString() + ")";
    }
}

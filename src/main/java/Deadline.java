public class Deadline extends Task {
    private final Datetime dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = new Datetime(dueDate);
    }

    public Deadline(String taskName, String byWhen, boolean isDone) {
        super(taskName, isDone);
        this.byWhen = byWhen;
    }

    public String fileSaveFormat() {
        return String.format("D | %d | %s | %s", this.isDone() ? 1 : 0, this.taskName(), this.byWhen);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate.toString() + ")";
    }
}

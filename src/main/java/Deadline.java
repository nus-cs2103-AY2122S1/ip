public class Deadline extends Task {
    private final Datetime dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = new Datetime(dueDate);
    }

    public Deadline(String taskName, String dueDate, boolean isDone) {
        super(taskName, isDone);
        this.dueDate = new Datetime(dueDate);
    }

    public String fileSaveFormat() {
        return String.format("D | %d | %s | %s", this.isDone() ? 1 : 0, this.taskName(),
                this.dueDate.getDatetimeString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate.toString() + ")";
    }
}

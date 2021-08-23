public class Deadline extends Task {

    public Deadline(String description, String by) {
        super(description);
        this.dueDate = by;
        this.taskType = "D";
    }

    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        this.dueDate = by;
        this.taskType = "D";
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (by: " + dueDate + ")";
    }
}
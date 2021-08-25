public class Deadline extends Task {
    private String date;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    public Deadline(String taskName, boolean isDone, String date) {
        super(taskName, isDone);
        this.date = date;
    }

    @Override
    public String toSaveData() {
        return "D|" + super.toSaveData() + "|" + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}

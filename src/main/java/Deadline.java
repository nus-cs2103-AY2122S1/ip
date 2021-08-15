public class Deadline extends Task {
    protected String finishDate;

    public Deadline(String taskDescription, String finishDate) {
        super(taskDescription);
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.finishDate);
    }
}

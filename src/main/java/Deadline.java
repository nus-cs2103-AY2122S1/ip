public class Deadline extends Task{
    private String taskType = "D";
    private String date;

    public Deadline(String deadline, String date) {
        super(deadline);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", this.taskType, super.toString(), this.date);
    }
}

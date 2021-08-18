public class Deadline extends Task{
    private String date;

    public Deadline(String task, String date) {
        super(task, "D");
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", this.getTaskType(), this.getCompletedMarker(), this.getTask(), this.date);
    }
}

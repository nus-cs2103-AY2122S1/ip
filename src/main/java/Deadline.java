public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTask() {
        return "D"; // mark done task with X
    }

    public String getDate() {
        return "(by: " + date + ")";
    }
}

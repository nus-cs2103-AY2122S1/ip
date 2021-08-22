public class Deadline extends Task {
    protected String date;
    protected String taskType = "[D]";


    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, String date, boolean isDone) {
        super(description);
        this.date = date;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (by: " + this.date + ")";
    }


}

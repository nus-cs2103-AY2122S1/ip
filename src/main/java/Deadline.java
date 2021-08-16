public class Deadline extends Task{
    protected String description;
    protected boolean isDone;
    final String DEADLINE = "[D]";
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    public String getDeadline() {
        return this.date;
    }

    @Override
    public String getStatusAndDescription() {return DEADLINE + this.getStatusIcon() + " " + this.getDescription() + " (by: " + this.date + ")"; }

}

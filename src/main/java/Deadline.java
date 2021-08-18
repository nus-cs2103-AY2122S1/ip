public class Deadline extends Task{
    protected String deadline;
    protected String logo = "[D]";

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String logo() {
        return logo;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() +  " " + super.description + " (by: " + deadline + ")";
    }
}

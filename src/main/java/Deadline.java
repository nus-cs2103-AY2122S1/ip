public class Deadline extends Task{
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description.replace("deadline", ""), "[D]");
        if (deadline.equals("")) {
            throw new MissingDateException();
        } else {
            this.deadline = deadline.replace("by", "by:");
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(" + this.deadline + ")";
    }
}

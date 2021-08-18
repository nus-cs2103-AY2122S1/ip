public class Deadline extends Task{
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description.replace("deadline ", ""), "[D]");
        this.deadline = deadline.replace("by", "by:");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(" + this.deadline + ")";
    }
}

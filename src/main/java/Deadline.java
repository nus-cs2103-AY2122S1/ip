public class Deadline extends Task{
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description, "[D]");
        if (deadline.equals("")) {
            throw new MissingDateException();
        } else {
            this.deadline = "by:" + deadline;
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(" + this.deadline + ")";
    }
}

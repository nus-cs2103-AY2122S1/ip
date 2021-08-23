public class Event extends Task{
    private String deadline;

    public Event(String description, String deadline) {
        super(description, "[E]");
        if (deadline.equals("")) {
            throw new MissingDateException();
        } else {
            this.deadline = "at:" + deadline;
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(" + this.deadline + ")";
    }
}

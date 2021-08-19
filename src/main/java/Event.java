public class Event extends Task{
    private String deadline;

    public Event(String description, String deadline) {
        super(description.replace("event", ""), "[E]");
        if (deadline.equals("")) {
            throw new MissingDateException();
        } else {
            this.deadline = deadline.replace("at", "at:");
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(" + this.deadline + ")";
    }
}

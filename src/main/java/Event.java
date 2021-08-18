public class Event extends Task{
    private String deadline;

    public Event(String description, String deadline) {
        super(description.replace("event ", ""), "[E]");
        this.deadline = deadline.replace("at", "at:");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(" + this.deadline + ")";
    }
}

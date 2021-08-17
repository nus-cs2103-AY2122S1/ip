public class Event extends Task {
    protected String date;
    protected String taskType = "[E]";

    public Event(String description, String date) {
        super(description);

        this.date = date;
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (at: " + this.date + ")";
    }


}

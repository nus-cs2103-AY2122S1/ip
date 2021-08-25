public class Event extends Task{
    protected String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ")";
    }

    @Override
    public String PopulateSaveData() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.period;
    }
}

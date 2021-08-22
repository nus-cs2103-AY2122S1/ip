public class Event extends Task {
    private final String period;

    public Event(String taskName, String period) {
        super(taskName);
        this.period = period;
    }

    @Override
    public String listEntry() {
        return "[E]" + super.listEntry() + " (at: " + period + ")";
    }

    @Override
    public String databaseEntry() {
        return "E" + super.databaseEntry() + " | " + period;
    }
}

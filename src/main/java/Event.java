public class Event extends Task {
    private final String startDate;

    Event(String name, String startDate) {
        super(name);
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startDate);
    }
}

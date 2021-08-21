public class Event extends Task {
    private final String startDate;

    Event(String name, String startDate) {
        super(name);
        this.startDate = startDate;
    }

    Event(String name, String startDate, boolean isComplete) {
        super(name, isComplete);
        this.startDate = startDate;
    }

    @Override
    public String toFile() {
        return String.format("E | %s | %s", super.toFile(), startDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startDate);
    }
}

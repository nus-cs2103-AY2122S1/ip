public class Event extends TimedTask {
    public Event(String description, String time) {
        super(description, 'E', time);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), getTimeDesc());
    }
}

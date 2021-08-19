public class Event extends Task {
    private String time;
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[T]%s (at: %s)", super.toString(), time);
    }
}

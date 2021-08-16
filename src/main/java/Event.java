public class Event extends Task {
    private String duration;

    Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    public String logo() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.duration + ")";
    }

    public String getDuration() {
        return this.duration;
    }
}

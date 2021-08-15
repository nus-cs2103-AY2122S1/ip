public class Event extends Task {
    private String duration;

    public Event(String desc, String duration) {
        super(desc);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[D]" + this.statusIcon() + this.getDesc() + " (at: " + this.duration + ")";
    }
}

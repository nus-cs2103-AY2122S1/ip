public class Event extends Task{
    private final String time;
    public Event(String description) {
        super(description);
        this.type = 'D';
        this.time = "";
    }

    public Event(String description, String time) {
        super(description);
        this.type = 'D';
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.time + ")";
    }
}

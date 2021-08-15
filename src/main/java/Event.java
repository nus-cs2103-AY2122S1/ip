public class Event extends Task {
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }


    @Override
    public String toString() {
        return "[E][" + this.getStatus() + "] " + this.name
                + "(at: " + time + ")";
    }
}

public class Event extends Task{

    private String date;
    private String time;
    public Event(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s %s)", this.date, this.time);
    }
}

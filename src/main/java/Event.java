public class Event extends TaskItem {

    protected String date;
    protected String time;

    public Event(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.date + " " + this.time + ")";
    }
}

public class Event extends Task {
    private String name;
    private String date;

    public Event(String name, String date) {
        super(name);
        this.name = name;
        this.date = date;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }
}
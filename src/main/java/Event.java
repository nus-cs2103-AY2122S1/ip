public class Event extends Task{
    private String schedule;

    public Event(String description, String s) {
        super(description);
        this.schedule = s;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.schedule + ")";
    }
}

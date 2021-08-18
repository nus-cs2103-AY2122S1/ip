public class Event extends Task{
    String time;

    public Event(String description, String time) {
        super(description, "[E]");
        this.time = time;
    }


    @Override
    public String toString() {
        return super.toString() + " (at: " + this.time + ")";
    }
}

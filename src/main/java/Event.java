public class Event extends Task{
    String time;

    public Event(String name) {
        super(name.substring(0, name.indexOf(" /at ") + 1));
        this.time = name.substring(name.indexOf(" /at ") + 5);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time + ")";
    }
}

public class Event extends Task{
    protected String at;

    public Event(String[] desc) {
        super(desc[1], false);
        this.at = desc[2];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")\n";
    }
}

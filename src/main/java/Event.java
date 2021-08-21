public class Event extends Task {

    private final String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String print() {
        return String.format("E,%d,%s,%s",isCompleted() ? 1 : 0, this.getName(), this.getAt());
    }
}

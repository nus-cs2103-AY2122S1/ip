public class Event extends Task {

    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String getRecordString() {
        int done = this.isDone ? 1 : 0;
        String record = String.format("E | %d | %s | %s", done, this.name, this.at);
        return record;
    }
}

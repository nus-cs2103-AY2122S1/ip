public class Event extends Task {
    String at;
    public Event(String desc, String at) throws Exception {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}

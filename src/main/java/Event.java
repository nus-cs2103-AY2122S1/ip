public class Event extends Task {
    String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public Event(String name, String at, boolean isDone) {
        super(name, isDone);
        this.at = at;
    }

    @Override
    public String toStringInStorage() {
        int done = this.isDone ? 1 : 0;
        return String.format("E/%d/%s/%s", done, this.name, this.at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at:%s)", super.toString(), this.at);
    }
}

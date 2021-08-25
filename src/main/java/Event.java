public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String num, String description, String at) {
        this(description, at);
        this.isDone = !num.equals("0");
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String getFileString() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.at);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
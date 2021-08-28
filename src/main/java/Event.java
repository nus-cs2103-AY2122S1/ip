public class Event extends Task{

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getFileString() {
        int i = this.isDone ? 1 : 0;
        return "E | " + i + " | " + this.description + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}
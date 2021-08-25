public class Event extends Task {
    protected String at;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    @Override
    public String formatChnage() {
        String mark = isDone ? "1" : "0";
        return "E | " + mark + " | " + this.description +" | " + this.at;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

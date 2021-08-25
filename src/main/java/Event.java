public class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }


    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.time);
    }


    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}

public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder deadlineLine = new StringBuilder();
        if (this.isDone) {
            deadlineLine.append("[E][x]");
        } else {
            deadlineLine.append("[E][ ]");
        }
        deadlineLine.append(this.description.replaceFirst("event", "") + "(at:" + this.time + ")");
        return deadlineLine.toString();
    }
}

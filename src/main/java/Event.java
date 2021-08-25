public class Event extends Task {
    private String date;

    public Event(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toSaveData() {
        return "E|" + super.toSaveData() + "|" + this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}

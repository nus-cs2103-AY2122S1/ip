public class Event extends Task{
    String time;

    public Event(String description, String time) {
        super(description, "[E]", false);
        this.time = time;
    }

    public Event(String description, String time, boolean status) {
        super(description, "[E]", status);
        this.time = time;
    }

    public String getformmatedData() {
        String formmatedStatus = super.isDone() ? "1|" : "0|";
        return "E|" + formmatedStatus + super.getDescription() + "|" + time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.time + ")";
    }
}

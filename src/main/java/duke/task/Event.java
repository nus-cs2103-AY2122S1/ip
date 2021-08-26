package duke.task;

public class Event extends Task {
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String convertToSaveFormat() {
        return "E|" + super.convertToSaveFormat() + "|" + time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}

public class Event extends Task {

    private final String timing;

    public Event(String task) {
        super(task.split(" /at ")[0]);
        this.timing = task .split(" /at ")[1];
    }

    @Override
    public String getDescription() {
        return "[E] " + super.getDescription() + " (at: " + this.timing + ")";
    }


    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.timing + ")";
    }
}

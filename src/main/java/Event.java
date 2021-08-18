public class Event extends Task {

    private final String timing;

    public Event(String task) {
        super(task.split(" /")[0]);
        this.timing = task .split(" /")[1].substring(3);
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

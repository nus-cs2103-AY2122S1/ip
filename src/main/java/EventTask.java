public class EventTask extends Task {

    private String time;

    /**
     * Constructor for EventTask object.
     * @param name name of task.
     * @param time time of event.
     */
    public EventTask(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}

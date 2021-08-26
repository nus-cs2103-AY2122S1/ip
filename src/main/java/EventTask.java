public class EventTask extends Task {

    private String time;

    /**
     * Constructor for EventTask object.
     * @param name name of task.
     * @param isDone whether or not task is done.
     * @param time time of event.
     */
    public EventTask(String name, boolean isDone, String time) {
        super(name, isDone);
        this.time = time;
    }

    public EventTask(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String formatForFile() {
        return "E" + super.formatForFile() + SAVE_DATA_MARKER + this.time + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}

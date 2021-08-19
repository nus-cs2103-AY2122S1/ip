public class Event extends Task {

    private final String taskName;
    public Event(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        if (taskName.contains("/at")) {
            return "[E]" + super.toString().
                    replaceFirst("/", "(") + ")";
        } else {
            return "[E]" + super.toString() + " (unknown timing)";
        }
    }
}

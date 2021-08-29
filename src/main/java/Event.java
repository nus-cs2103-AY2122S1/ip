public class Event extends TaskWithDateTime {
    public Event(TaskType type, String description, String dateTimeInput) {
        super(type, description, dateTimeInput);
    }

    public Event(TaskType type, String description, String dateTimeInput, boolean isDone) {
        super(type, description, dateTimeInput, isDone);
    }

    @Override
    public String dateTimeDetails() {
        return "(at: " + super.getDateTimeOutput() +")";
    }

    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getAbbr() + "] " + super.toString();
    }
}

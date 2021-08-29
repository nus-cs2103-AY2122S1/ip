public class Event extends TaskWithDateTime {
    public Event(TaskType type, String description, String dateTime) {
        super(type, description, dateTime);
    }

    public Event(TaskType type, String description, String dateTime, boolean isDone) {
        super(type, description, dateTime, isDone);
    }

    @Override
    public String dateTimeDetails() {
        return "(at: " + super.getDateTime() +")";
    }

    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getAbbr() + "] " + super.toString();
    }
}

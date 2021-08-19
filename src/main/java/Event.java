public class Event extends TaskWithDateTime {
    public Event(TaskType taskType, String taskDescription, String dateTime) {
        super(taskType, taskDescription, dateTime);
    }

    @Override
    public String dateTimeDetails() {
        return "(at: " + super.getDateTime() +")";
    }

    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getAbbr() + "]" + super.toString();
    }
}

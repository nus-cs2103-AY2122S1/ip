public class Deadline extends TaskWithDateTime {
    public Deadline(TaskType type, String description, String dateTime) {
        super(type, description, dateTime);
    }

    public Deadline(TaskType type, String description, String dateTime, boolean isDone) {
        super(type, description, dateTime, isDone);
    }

    @Override
    public String dateTimeDetails() {
        return "(by: " + super.getDateTime() + ")";
    }

    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getAbbr() + "] " + super.toString();
    }
}

public class Deadline extends TaskWithDateTime {
    public Deadline(TaskType type, String description, String dateTimeInput) {
        super(type, description, dateTimeInput);
    }

    public Deadline(TaskType type, String description, String dateTimeInput, boolean isDone) {
        super(type, description, dateTimeInput, isDone);
    }

    @Override
    public String dateTimeDetails() {
        return "(by: " + super.getDateTimeOutput() + ")";
    }

    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getAbbr() + "] " + super.toString();
    }
}

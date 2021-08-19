public class Deadline extends TaskWithDateTime {
    public Deadline(TaskType taskType, String taskDescription, String dateTime) {
        super(taskType, taskDescription, dateTime);
    }

    @Override
    public String dateTimeDetails() {
        return "(by: " + super.getDateTime() +")";
    }

    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getAbbr() + "]" + super.toString();
    }
}

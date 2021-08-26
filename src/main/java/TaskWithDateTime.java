public abstract class TaskWithDateTime extends Task {
    private String dateTime;

    public TaskWithDateTime(TaskType type, String description, String dateTime) {
        this(type, description, dateTime, false);
    }

    public TaskWithDateTime(TaskType type, String description, String dateTime, boolean isDone) {
        super(type, description, isDone);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public abstract String dateTimeDetails();

    @Override
    public String toString() {
        return super.toString() + " " + dateTimeDetails();
    }
}

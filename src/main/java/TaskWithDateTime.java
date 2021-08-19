public abstract class TaskWithDateTime extends Task {
    protected String dateTime;

    public TaskWithDateTime(TaskType type, String description, String dateTime) {
        super(type, description);
        this.dateTime = dateTime;
    }

    protected String getDateTime() {
        String[] dateTimeParts = dateTime.split(" ", 2);
        return dateTimeParts[1];
    }

    public abstract String dateTimeDetails();

    @Override
    public String toString() {
        return super.toString() + dateTimeDetails();
    }
}

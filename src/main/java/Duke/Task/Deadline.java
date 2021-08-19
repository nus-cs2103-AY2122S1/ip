package Duke.Task;

public class Deadline extends Task {
    private static final String TASK_TYPE_ICON = "D";
    private static final String END_DATE_EMPTY_MESSAGE = "Deadline end date cannot be empty.";

    private final String endDate;

    public Deadline(String description, String endDate) throws InvalidTaskException {
        super(description);
        if (endDate.isEmpty()) throw new InvalidTaskException(END_DATE_EMPTY_MESSAGE);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.endDate);
    }

    @Override
    protected String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}

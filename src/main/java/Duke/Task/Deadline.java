package Duke.Task;

public class Deadline extends Task {
    public static final String TASK_TYPE_ICON = "D";
    private static final String END_DATE_EMPTY_MESSAGE = "Deadline end date cannot be empty.";

    private final String endDate;

    public Deadline(String description, String endDate, boolean isDone) throws InvalidTaskException {
        super(description, isDone);
        if (endDate.isEmpty()) throw new InvalidTaskException(END_DATE_EMPTY_MESSAGE);
        this.endDate = endDate;
    }

    public Deadline(String description, String endDate) throws InvalidTaskException {
        this(description, endDate, false);
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.endDate);
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}

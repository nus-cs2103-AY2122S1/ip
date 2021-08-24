package Duke.Task;

import java.time.LocalDate;

public class Deadline extends Task {
    private static final String TASK_TYPE_ICON = "D";
    private static final String END_DATE_EMPTY_MESSAGE = "Deadline end date cannot be empty.";

    private final LocalDate endDate;

    public Deadline(String description, LocalDate endDate) throws InvalidTaskException {
        super(description);
        if (endDate == null) throw new InvalidTaskException(END_DATE_EMPTY_MESSAGE);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String dateString = this.endDate.format(DATE_DISPLAY_FORMAT);
        return super.toString() + String.format(" (by: %s)", dateString);
    }

    @Override
    protected String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}

package duke.tasks;

import java.time.LocalDate;

public class RecurringTask extends Task {
    private static final String IDENTIFIER = "R";

    private int intervalInDays;
    private LocalDate nextDeadline;

    public RecurringTask(String taskName, LocalDate nextDeadline, int intervalInDays) {
        super(taskName);
        this.nextDeadline = nextDeadline;
        this.intervalInDays = intervalInDays;
    }

    @Override
    public String toString() {
        return String.format("[R]%s (by: %s, every %d days)", super.toString(),
                nextDeadline, intervalInDays);
    }

    public String getIdentifier() {
        return IDENTIFIER;
    }

    public String getDetailsWithDelimiter(String delimiter) {
        return String.format("%s%s%s%s%d", taskName, delimiter, nextDeadline,
                delimiter, intervalInDays);
    }

    /**
     * Returns the same task but at the next deadline
     * @return A recurring task object representing the same task but one deadline later
     */
    @Override
    public Task getNextTask() {
        return new RecurringTask(taskName, nextDeadline.plusDays(intervalInDays),
                intervalInDays);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RecurringTask)) {
            return false;
        }
        RecurringTask r = (RecurringTask) o;
        return taskName.equals(r.taskName) && isDone == r.isDone &&
                nextDeadline.equals(r.nextDeadline) &&
                intervalInDays == r.intervalInDays;
    }
}

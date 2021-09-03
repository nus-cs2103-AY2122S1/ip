package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class TaskWithDateTime extends Task {
    /**
     * These are class fields of Deadline.
     */
    protected LocalDate date;
    protected LocalTime time;

    /**
     * This is a TaskWithDateTime Constructor.
     *
     * @param description A String representing description of task.
     */
    public TaskWithDateTime(String description) {
        super(description);
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof ToDo) {
            return -1; // Deadline and Events in front of ToDo
        } else if (o instanceof TaskWithDateTime) {
            TaskWithDateTime taskWithDateTime = (TaskWithDateTime) o;
            return compareDateTimeDescription(taskWithDateTime.date, taskWithDateTime.time, taskWithDateTime.description);
        } else {
            return 0;
        }
    }

    private int compareDateTimeDescription(LocalDate otherDate, LocalTime otherTime, String otherDescription) {
        int compareDateResult = this.date.compareTo(otherDate);
        if (compareDateResult == 0) { // same date
            int compareTimeResult = this.time.compareTo(otherTime);
            if (compareTimeResult == 0) { // same time
                return this.description.compareTo(otherDescription);
            } else {
                return compareTimeResult;
            }
        } else {
            return compareDateResult;
        }
    }
}

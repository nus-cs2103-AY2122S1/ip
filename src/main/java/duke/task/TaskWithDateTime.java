package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This is a TaskWithDateTime class that extends from Task.
 * This class contains Date and Time fields and allow for comparison using Date, Time and Description,
 * in order of priority.
 */
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
        if (this == o) {
            return 0;
        }
        if (o instanceof ToDo) {
            return -1; // Deadline and Events in front of ToDo
        } else if (o instanceof TaskWithDateTime) {
            TaskWithDateTime taskWithDateTime = (TaskWithDateTime) o;
            return compareDateTimeDescription(taskWithDateTime);
        } else {
            return 0;
        }
    }

    private int compareDateTimeDescription(TaskWithDateTime otherTask) {
        int compareDateResult = this.date.compareTo(otherTask.date);
        if (compareDateResult == 0) { // same date
            int compareTimeResult = this.time.compareTo(otherTask.time);
            if (compareTimeResult == 0) { // same time
                return this.description.compareTo(otherTask.description);
            } else {
                return compareTimeResult;
            }
        } else {
            return compareDateResult;
        }
    }
}

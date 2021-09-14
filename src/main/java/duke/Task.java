package duke;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A Parent class for the different types of input to the Task List.
 */
public class Task implements Comparable<Task> {
    protected String description;

    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalTime startTime;
    protected LocalTime endTime;

    protected boolean isDone;

    /**
     * A public constructor to create a task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public Task(String description, LocalDate byDate, LocalTime byTime) {
        this.description = description;
        this.isDone = false;
        this.startDate = byDate;
        this.startTime = byTime;
    }

    public Task(String description, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        this.description = description;
        this.isDone = false;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of the task's status.
     * @return the string representation of the task's status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task.
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Returns the string representation of the task
     * to be saved into the file.
     * @return the string representation of the task
     * to be saved into the file.
     */
    public String toStore() {
        return this.getStatusIcon() + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    @Override
    public int compareTo(Task other) {
        if (this.startDate == null) {
            return 1;
        }
        if (other.startDate == null) {
            return -1;
        }
        if (this.startDate.isBefore(other.getStartDate())) {
            return -1;
        } else if (this.startDate.isEqual(other.getStartDate())) {
            if (this.startTime.isBefore(other.getStartTime())) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return this.description.compareTo(other.getDescription());
        }
    }

}

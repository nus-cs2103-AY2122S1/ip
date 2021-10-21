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
     * A public constructor to create a task of type Todo.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * A public constructor to create a task of type Deadline.
     * @param description The description of the task.
     * @param byDate The end date of the task
     * @param byTime The end time of the task.
     */
    public Task(String description, LocalDate byDate, LocalTime byTime) {
        this.description = description;
        this.isDone = false;
        this.startDate = byDate;
        this.startTime = byTime;
    }


    /**
     * A public constructor to create a task of type Event.
     * @param description The description of the task.
     * @param startDate The start date of the task.
     * @param startTime The start time of the task.
     * @param endDate The end date of the task
     * @param endTime The end time of the task.
     */
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


    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Returns the deadline date for Deadline or the start date of the Event.
     * @return the deadline date for Deadline or the start date of the Event.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }


    /**
     * Returns the deadline time for Deadline or the start time of the Event.
     * @return the deadline time for Deadline or the start time of the Event.
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end date of the Event.
     * @return the end date of the Event.
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Returns the end time of the Event.
     * @return the end time of the Event.
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }


    /**
     * Overrides the method compareTo in Comparable<Task>.
     * Compares between two tasks based on their chronological order.
     * If there are no dates between two tasks, it is sorted in alphabetical order.
     * @param other the other task to be compared with the current one.
     * @return positive integer if
     */
    @Override
    public int compareTo(Task other) {
        if (this.startDate == null && other.startDate == null) {
            return this.description.compareTo(other.getDescription());
        }
        // one of the task is confimred not to be of todo type
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
        } else { //!this.startDate.isBefore(other.getStartDate())
            return 1;
        }
    }

}

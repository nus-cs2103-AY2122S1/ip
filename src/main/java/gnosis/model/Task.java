package gnosis.model;

import java.time.LocalDateTime;

/**
 * The Task class encapsulates a task with a String and
 * whether the task is done or not.
 * @author Pawandeep Singh
 *
 * */
public class Task {

    /** Type of task in character */
    private char taskType;

    /** Task name */
    private String taskName;

    /** Date/Time of task scheduled/created */
    private LocalDateTime dateTime;

    /** Specifies whether task is marked done or not */
    private boolean isDone;


    /**
     * Task constructor to initialise task.
     * Task initialised with task name and type.
     *
     * @param taskName Task name.
     * @param type Type of task.
     */
    public Task(String taskName, TaskType type) {
        this.taskName = taskName;
        this.taskType = TaskType.getTask(type);
        isDone = false;
        dateTime = LocalDateTime.now();
    }

    /**
     * Task constructor to initialise task.
     * Task initialised with task name, type and datetime.
     *
     * @param taskName Task name.
     * @param type Type of task.
     * @param dateTime Date/Time of task created/scheduled.
     */
    public Task(String taskName, TaskType type, LocalDateTime dateTime) {
        this.taskName = taskName;
        this.taskType = TaskType.getTask(type);
        this.dateTime = dateTime;
        isDone = false;
    }

    /**
     * Task constructor to initialise task.
     * Task initialised with task name, type, datetime,
     * and whether task is done or not.
     *
     * @param taskName Task name.
     * @param type Type of task.
     * @param dateTime Date/Time of task created/scheduled.
     * @param isDone Marks whether task is done or not.
     */
    public Task(String taskName, TaskType type, LocalDateTime dateTime, boolean isDone) {
        this.taskName = taskName;
        this.taskType = TaskType.getTask(type);
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    /**
     * Retrieves task name.
     *
     * @return Name of task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Retrieves task date/time.
     *
     * @return Task date/time.
     */
    public LocalDateTime getDatetime() {
        return this.dateTime;
    }

    /**
     * Sets whether task is done or not.
     *
     * @param done Marks whether task is done or not.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Retrieve UI representative of whether Task,
     * is done or not.
     * 'X' - Task is done.
     * ' ' - Task is not done.
     *
     * @return Character symbol of task completion status.
     */
    public char isTaskDone() {
        return this.isDone ? 'X' : ' ';
    }

    /**
     * Retrieves character representative of task type
     *
     * 'T' - 'T0DO' type.
     * 'E' - 'EVENT' type.
     * 'D' - 'DEADLINE' type.
     *
     * @return Character of task type.
     */
    public char getTaskType() {
        return this.taskType;
    }

    /**
     * Retrieves string representative of task.
     *
     * @return String representative of task.
     */
    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]"
                + "[" + this.isTaskDone() + "]"
                + " " + this.taskName;
    }
}

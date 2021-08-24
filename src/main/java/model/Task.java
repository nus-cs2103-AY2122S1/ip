package model;

import java.time.LocalDateTime;

/**
 * The model.Task class encapsulates a task with a String and
 * whether the task is done or not.
 *
 * @author Pawandeep Singh
 * @version Level-3
 *
 * */
public class Task {

    private final char TASK_TYPE;
    private String taskName;
    private LocalDateTime dateTime;
    private boolean isDone;


    public Task(String taskName, TaskType type) {
        this.taskName = taskName;
        this.TASK_TYPE = TaskType.getTask(type);
        isDone = false;
        dateTime =  LocalDateTime.now();
    }


    public Task(String taskName, TaskType type, LocalDateTime dateTime) {
        this.taskName = taskName;
        this.TASK_TYPE = TaskType.getTask(type);
        this.dateTime = dateTime;
        isDone = false;
    }

    public Task(String taskName, TaskType type, LocalDateTime dateTime, boolean isDone) {
        this.taskName = taskName;
        this.TASK_TYPE = TaskType.getTask(type);
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDateTime getDatetime() {
        return this.dateTime;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public char isTaskDone() {
        return this.isDone ? 'X' : ' ';
    }

    public char getTaskType() {
        return this.TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]"
                + "["+ this.isTaskDone()  + "]"
                + " " + this.taskName;
    }
}

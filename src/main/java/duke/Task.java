package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task class that is used by duke class to create tasks.
 * Stores information like description, task type and whether if it is done.
 * Stores date if the task is a deadline or event.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected LocalDate date;

    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public Task(String description, String taskType, String by) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        if (by.contains("/")) {
            date = LocalDate.parse(by.split("/")[2] + "-" + by.split("/")[1] + "-" +
                    (Integer.parseInt(by.split("/")[0]) < 10 ? "0" + by.split("/")[0] :
                            by.split("/")[0]));
        } else {
            date = LocalDate.parse(by);
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getBy() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

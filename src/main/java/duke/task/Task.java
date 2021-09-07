package duke.task;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task to be done.
 */
public class Task implements Serializable {
    //Description of Task
    protected String description;
    //Flag for whether a task is done, default false
    protected boolean isDone;
    //date of event in format LocalDate
    protected LocalDate Date;
    //time of event in format String
    protected String Time;

    /**
     * Constructs a Task with a String description.
     *
     * @param description String description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setDate(String Date) {
        String date = Date.replace("/", "-");
        this.Date = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    /**
     * Converts Task to String, showing the status of completion and description.
     *
     * @return Status of completion and description of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

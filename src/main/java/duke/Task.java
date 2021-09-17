package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    /** The task itself, in String form */
    private String task;

    /** The done status of the task */
    private Boolean isDone;

    /** The type of the task, defined by the enum duke.Type */
    private Type type;

    /** The datetime object for the event */
    private LocalDateTime datetime;

    /**
     * Constructor to initialise the task
     * @param task The task itself.
     * @param type The type of the task defined by the enum duke.Type.
     */
    public Task(String task, Type type) {
        this.task = task;
        this.isDone = false;
        this.type = type;
        this.datetime = null;
    }

    /**
     * Constructor with 3 params
     * @param task Task string.
     * @param type Type enum.
     * @param isDone Boolean done.
     */
    public Task(String task, Type type, Boolean isDone) {
        this.task = task;
        this.isDone = isDone;
        this.type = type;
        this.datetime = null;
    }

    /**
     * Constructor with 3 params
     * @param task Task string.
     * @param type Type enum.
     * @param isDone Boolean done.
     * @param datetime Datetime datetime.
     */
    public Task(String task, Type type, Boolean isDone, LocalDateTime datetime) {
        this.task = task;
        this.isDone = isDone;
        this.type = type;
        this.datetime = null;
    }

    /**
     * Empty default constructor.
     */
    public Task() {
        this.task = "";
        this.isDone = false;
        this.type = Type.TODO;
        this.datetime = null;
    }

    /**
     * Getter to see if the task is done.
     * @return Boolean about whether the task is done.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Setter to set the task as done or not done.
     * @param isDone Boolean about whether the task is done.
     */
    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * String representation of the done status of task
     * @return String representation of the done status of task.
     */
    public String checkBox() {
        if (this.isDone()) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public Type getType() {
        return this.type;
    }

    public String getTask() {
        return task;
    }

    /**
     * String to write to file.
     * @return File write string.
     */
    public String fileWriteString() {
        String res = "";
        res += Type.typeString(getType()) + ",";
        res = isDone() ? res + "1," : res + "0,";
        res += task + ",";
        res += getDatetimeString();
        return res;
    }

    /**
     * String representation of the type of task.
     * @return String representation of the type of task.
     */
    public abstract String typeString();
    public abstract String taskString();

    public LocalDateTime getDatetime() {
        return this.datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getDatetimeString() {
        return this.datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s%s %s", typeString(), checkBox(), taskString());
    }
}

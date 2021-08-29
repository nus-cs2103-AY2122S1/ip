package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    /** The task itself, in String form */
    private String task;

    /** The done status of the task */
    private Boolean done;

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
        this.done = false;
        this.type = type;
        this.datetime = LocalDateTime.now();
    }

    public Task(String task, Type type, Boolean done) {
        this.task = task;
        this.done = done;
        this.type = type;
        this.datetime = LocalDateTime.now();
    }

    public Task(String task, Type type, Boolean done, LocalDateTime datetime) {
        this.task = task;
        this.done = done;
        this.type = type;
        this.datetime = datetime;
    }

    public Task() {
        this.task = "";
        this.done = false;
        this.type = Type.TODO;
        this.datetime = LocalDateTime.now();
    }

    /**
     * Getter to see if the task is done.
     * @return Boolean about whether the task is done.
     */
    public Boolean isDone() {
        return this.done;
    }

    /**
     * Setter to set the task as done or not done.
     * @param done Boolean about whether the task is done.
     */
    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * String representation of the done status of task
     * @return String representation of the done status of task.
     */
    public String checkBox() {
        if(this.isDone()) {
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

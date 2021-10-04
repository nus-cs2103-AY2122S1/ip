package duke;

import java.time.LocalDateTime;

public class Todo extends Task {


    /**
     * Constructor to initialise the task
     * @param task The task itself.
     * @param type The type of the task defined by the enum duke.Type.
     */
    public Todo(String task, Type type) {
        super(task, type);
    }

    public Todo(String task, Type type, Boolean done) {
        super(task, type, done);
    }

    public Todo(String task, Type type, Boolean done, LocalDateTime datetime) {
        super(task, type, done, datetime);
    }

    public Todo() {
        super();
    }

    /**
     * String reflecting type of task.
     * @return String reflecting type of task.
     */
    public String typeString() {
        return "[T]";
    }

    /**
     * String to be written to file.
     * @return String to be written to file.
     */
    public String fileWriteString() {
        String res = "";
        res += Type.typeString(getType()) + ",";
        res = isDone() ? res + "1," : res + "0,";
        res += getTask();
        return res;
    }

    /**
     * String reflecting datetime of task.
     * @return String reflecting datetime of task.
     */
    @Override
    public String getDatetimeString() {
        return "";
    }

    /**
     * String reflecting task itself.
     * @return String reflecting task itself.
     */
    public String taskString() {
        return getTask();
    }

}

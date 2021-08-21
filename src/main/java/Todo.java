import java.time.LocalDateTime;

public class Todo extends Task {


    /**
     * Constructor to initialise the task
     * @param task The task itself.
     * @param type The type of the task defined by the enum Type.
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

    public String typeString() {
        return "[T]";
    }

    public String fileWriteString() {
        String res = "";
        res += Type.typeString(getType()) + ",";
        res = isDone() ? res + "1," : res + "0,";
        res += getTask();
        return res;
    }

    @Override
    public String getDatetimeString() {
        return "";
    }

    public String taskString() {
        return getTask();
    }

}

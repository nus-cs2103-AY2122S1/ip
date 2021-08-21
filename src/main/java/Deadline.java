import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task{

    /**
     * Constructor to initialise the task
     * @param task The task itself.
     * @param type The type of the task defined by the enum Type.
     */
    public Deadline(String task, Type type) {
        super(task, type);
    }

    public Deadline(String task, Type type, Boolean done, LocalDateTime datetime) {
        super(task, type, done, datetime);
    }

    public Deadline(String task, Type type, Boolean done) {
        super(task, type, done);
    }

    public Deadline() {
        super();
    }

    public String typeString() {
        return "[D]";
    }

    public String taskString() {
        return String.format("%s (%s: %s)", getTask(), Type.getConnector(getType()), getDatetimeString());
    }
}

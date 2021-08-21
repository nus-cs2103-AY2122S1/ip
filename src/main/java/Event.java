import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task{
    /**
     * Constructor to initialise the task
     * @param task The task itself.
     * @param type The type of the task defined by the enum Type.
     */
    public Event(String task, Type type) {
        super(task, type);
    }

    public Event(String task, Type type, Boolean done) {
        super(task, type, done);
    }

    public Event(String task, Type type, Boolean done, LocalDateTime datetime) {
        super(task, type, done, datetime);
    }

    public Event() {
        super();
    }

    public String typeString() {
        return "[E]";
    }

    public String taskString() {
        return String.format("%s (%s: %s)", getTask(), Type.getConnector(getType()), getDatetimeString());
    }
}

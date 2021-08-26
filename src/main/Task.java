import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {

    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyy HH:mm");

    private String description;
    private boolean isDone;
    protected LocalDateTime time = null;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, String time) throws DukeException {
        this(description);
        try {
            this.time = LocalDateTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect Time Format!");
        }
    }

    public void finishTask() {
        isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected String saveString() {
        return String.format("%s|%s|%s", isDone, description, time);
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}

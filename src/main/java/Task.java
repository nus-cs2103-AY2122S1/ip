import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyy HH:mm");

    private String description;
    private boolean isDone;
    protected LocalDateTime time;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, String time) {
        this(description);
        this.time = LocalDateTime.parse(time.trim());
    }

    public void finishTask() {
        isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

}

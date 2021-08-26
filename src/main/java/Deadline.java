import java.time.LocalDateTime;
public class Deadline extends Task{

    private Deadline(String description, LocalDateTime deadline) {
        super(description, "D", deadline);
    }

    public static Deadline create(String description, LocalDateTime time) {
        return new Deadline(description, time);
    }

    @Override
    public String toString() {
        return '[' + this.taskType + ']' + '[' + getStatusIcon()
                + ']' + ' ' + this.description
                + String.format(" (by: %s)", this.time.format(formatter));
    }
}

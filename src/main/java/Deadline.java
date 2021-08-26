import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toStringInStorage() {
        int done = this.isDone ? 1 : 0;
        return String.format("D/%d/%s/%s", done, this.name, this.by.toString().replace("T", " "));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy K:mm a");
        return String.format("[D]%s(by:%s)", super.toString(), this.by.format(formatter));
    }
}

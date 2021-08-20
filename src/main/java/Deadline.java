import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime time;

    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d h.mm a");
        return String.format("[D]%s(by: %s)", super.toString(), time.format(formatter));
    }

    public String save() {
        return String.format("D | %s| %s", super.save(), time);
    }
}

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime time;

    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(),  Messages.dateFormat(time));
    }

    public String save() {
        return String.format("D | %s| %s", super.save(),  Messages.dateFormat(time));
    }
}

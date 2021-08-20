import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime time;

    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {

        return String.format("[E]%s(at: %s)", super.toString(), Messages.dateFormat(time));
    }

    public String save() {
        return String.format("E | %s| %s", super.save(), Messages.dateFormat(time));
    }
}

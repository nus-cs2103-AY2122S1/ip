import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final String at;
    private final LocalDateTime datetime;

    Event(String name, String at, boolean isCompleted) {
        super(name, TaskType.E, isCompleted);
        this.at = at;
    public Event(String name, String at) throws DukeException {
        super(name, TaskType.D);
        try {
            this.datetime = LocalDateTime.parse(at.trim(), DateTimeFormatCreator.format());
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect form of date input. Please try yyyy-mm-dd HH:mm instead.");
        }
    }

    private String happeningWhen() {
        return " (at: " + this.at + ")";
        return " (at: "
                + this.datetime.toLocalDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.datetime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")";
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public Task updateName(String input) {
        return new Event(input, this.at, this.getCompleted());
    }

    @Override
    public Task complete() {
        return new Event(this.getName(), this.at, true);
    }

    @Override
    public String details() {
        String checkbox = "[" + ( getCompleted() ? "X" : " ") + "]";
        String details = taskTypeString() + checkbox + " " + this.getName();
        return details + happeningWhen();
    }

    @Override
    public String getLabel() {
        return this.getName() + "|" + this.getAt();
    }
}

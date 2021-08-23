import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private final String by;
    private final LocalDateTime datetime;

    Deadline(String name, String by, boolean isCompleted) {
        super(name, TaskType.D, isCompleted);
        this.by = by;
    public Deadline(String name, String by) throws DukeException {
        super(name, TaskType.D);
        try{
            this.datetime = LocalDateTime.parse(by.trim(), DateTimeFormatCreator.format());
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect form of date input. Please try yyyy-mm-dd HH:mm instead.");
        }
    }

    private String deadline() {
        return " (by: " + this.by + ")";
        return " (by: "
                + this.datetime.toLocalDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.datetime.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public Task updateName(String input) {
        return new Deadline(input, this.by, this.getCompleted());
    }

    @Override
    public Task complete() {
        return new Deadline(this.getName(), this.by, true);
    }

    @Override
    public String details() {
        String checkbox = "[" + ( getCompleted() ? "X" : " ") + "]";
        String details = taskTypeString() + checkbox + " " + this.getName();
        return details + deadline();
    }

    @Override
    public String getLabel() {
        return this.getName() + "|" + this.getBy();
    }

}

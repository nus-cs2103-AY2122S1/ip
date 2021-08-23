import java.time.LocalDate;

public class Event extends Task{
    private LocalDate time;

    public Event(String name, LocalDate time) {
        super(name, "E");
        this.time = time;
    }

    public Event(String name, LocalDate time, boolean completed) {
        super(name, completed, "E");
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    public String getSaveFormat() {
        return String.format(
                "%s|%s",
                super.getSaveFormat(),
                DukeDate.formatDateSave(this.time)
        );
    }
}

import java.time.LocalDate;

public class Deadline extends Task{

    private LocalDate by;

    public Deadline(String value, LocalDate by) {
        super(value);
        this.by = by;
    }

    @Override
    public LocalDate getTime() {
        return by;
    }

    @Override
    public String getType() {
        return "DEADLINE";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}

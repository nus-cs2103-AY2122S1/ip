import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;
    private String time;

    public Deadline(String name, LocalDate by, String time) {
        super(name);
        this.by = by;
        this.time = time;
    }

    public String getBy() {
        return this.by.toString();
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)",
                super.toString(),
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.getTime());
    }

    @Override
    public String print() {
        return String.format("D,%d,%s,%s %s", isCompleted() ? 1 : 0, this.getName(), this.getBy(), this.getTime());
    }
}

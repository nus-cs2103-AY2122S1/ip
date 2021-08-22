import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected final LocalDate deadline;
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    Deadline(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(super.getName(), true, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (deadline: %s)", super.toString()
                , deadline.format(formatter));
    }
}

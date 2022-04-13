package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Deadline extends Task {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected final LocalDate deadline;

    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public Deadline(String name, boolean isDone, List<String> tags, LocalDate deadline) {
        super(name, isDone, tags);
        this.deadline = deadline;
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(super.getName(), true, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (deadline: %s)", super.toString(),
                deadline.format(DATE_TIME_FORMATTER));
    }
}

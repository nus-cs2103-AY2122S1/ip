package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    private LocalDate deadline;

    public Deadline(String description, String by) {
        super(description);
        this.deadline = LocalDate.parse(by);
        this.by = by;
    }

    @Override
    public char getTaskType() { return 'D'; }

    @Override
    public String getTime() {
        return this.by;
    }

    @Override
    public String toString() {
        String formattedDate = this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "  [D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}

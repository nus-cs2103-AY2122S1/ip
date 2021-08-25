package bob.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    /** Deadline date of Deadline */
    private LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
     }

    @Override
    public String printTask() {
        return "[D] " + super.printTask() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
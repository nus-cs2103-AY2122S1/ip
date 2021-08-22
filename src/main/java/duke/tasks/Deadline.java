package duke.tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    LocalDate deadline;

    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    public String getIdentifier() {
        return "D";
    }

    public String getDetailsWithDelimiter(String delimiter) {
        return String.format("%s%s%s", taskName, delimiter, deadline);
    }
}

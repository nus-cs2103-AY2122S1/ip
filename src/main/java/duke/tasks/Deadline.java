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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline d = (Deadline) o;
        return taskName.equals(d.taskName) && done == d.done &&
                deadline.equals(d.deadline);
    }
}

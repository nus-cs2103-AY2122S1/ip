import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public Deadline(String title, LocalDate timeDue) {
        super(title, TypeIndicators.DEADLINE);
        this.timeDue = timeDue;
    }

    public Deadline(String title, LocalDate timeDue, boolean isDone) {
        super(title, TypeIndicators.DEADLINE);
        this.timeDue = timeDue;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of a Deadline.
     *
     * @return A string describing the Deadline.
     */
    @Override
    public String toString() {
        String formattedTimeDue = this.timeDue == null ? "" : this.timeDue.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + String.format(" (by: %s)", formattedTimeDue);
    }
}
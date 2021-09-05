import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event (String desc, LocalDate timeDue) {
        super(desc, Type.EVENT);
        this.timeDue = timeDue;
    }

    public Event(String title, LocalDate timeDue, boolean isDone) {
        super(title, Type.EVENT);
        this.timeDue = timeDue;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of a Event.
     *
     * @return A string describing the Event.
     */
    @Override
    public String toString() {
        String formattedTimeDue = this.timeDue == null ? "" : this.timeDue.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return super.toString() + String.format(" (at: %s)", formattedTimeDue);
    }
}

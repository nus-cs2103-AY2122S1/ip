package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task that is due by a specified date.
 *
 * @author Samay Sagar
 * @version CS2103 AY21/22 Sem 1
 */
//Solution below adapted from https://github.com/jovyntls/ip
public class Deadline extends Task {
    /**
     * A constructor for a Deadline that specifies whether or not it is completed.
     * @param description a String representing the title of the deadline
     * @param timeDue a LocalDate representing time at which the deadline is due
     */
    public Deadline(String description, LocalDate timeDue) {
        super(description, Type.DEADLINE);
        this.timeDue = timeDue;
    }

    /**
     * A constructor for a Deadline that specifies whether or not it is completed.
     * @param description a String representing the title of the deadline
     * @param timeDue a LocalDate representing time at which the deadline is due
     * @param isDone a boolean representing whether or not the deadline is completed
     */
    public Deadline(String description, LocalDate timeDue, boolean isDone) {
        super(description, Type.DEADLINE);
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
        String formattedTimeDue = timeDue == null
                ? ""
                : timeDue.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + String.format(" (by: %s)", formattedTimeDue);
    }
}

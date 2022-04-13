package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** Date aspect of the deadline */
    private LocalDate date;
    /** Time aspect of the deadline */
    private LocalTime time;

    /**
     * Constructor for deadline task.
     *
     * @param description description of deadline task.
     * @param deadline deadline of task in "yyyy-mm-dd HHmm" format.
     */
    public Deadline(String description, String deadline) {
        super(description, "[D]");
        this.date = LocalDate.parse(deadline.substring(0, 10));
        this.time = LocalTime.parse(deadline.substring(11), DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Constructor for deadline task.
     *
     * @param description Description of deadline task.
     * @param date Date aspect of the deadline in "MMM d yyyy" format.
     * @param time Time aspect of the deadline in "h:ma" format.
     */
    public Deadline(String description, String date, String time) {
        super(description, "[D]");
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("h:ma"));
    }

    /**
     * Returns the description of the deadline task.
     *
     * @return Description of deadline task.
     */
    @Override
    public String getDescription() {
        return super.getDescription()
                + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + this.time.format(DateTimeFormatter.ofPattern(" h:mma"))
                + ")";
    }
}



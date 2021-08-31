package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for event task.
     *
     * @param description Description of event task.
     * @param deadline Deadline of event task in "yyyy-mm-dd HHmm" format.
     */
    public Event(String description, String deadline) {
        super(description, "[E]");
        this.date = LocalDate.parse(deadline.substring(0, 10));
        this.time = LocalTime.parse(deadline.substring(11), DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Constructor for event task.
     *
     * @param description Description of event task.
     * @param date Date aspect of the deadline in "MMM d yyyy" format.
     * @param time Time aspect of the deadline in "h:ma" format.
     */
    public Event(String description, String date, String time) {
        super(description, "[E]");
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("h:ma"));
    }

    /**
     * Returns the description of the event task.
     *
     * @return Description of event task.
     */
    @Override
    public String getDescription() {
        return super.getDescription()
                + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + this.time.format(DateTimeFormatter.ofPattern(" h:mma"))
                + ")";
    }
}

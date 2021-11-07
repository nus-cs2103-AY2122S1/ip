package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Events are tasks that have a timeline.
 * @author Dominic Siew Zhen Yu
 */
public class Event extends Task {
    private String timeline;
    private LocalDateTime dateAndTime;
    private String taskIndicator = "[E]";

    /**
     * The constructor of the events class.
     * @param userInput the name of the task
     * @param timeline the period of which the event is taking place
     */
    public Event(String userInput, String timeline, boolean newInput) {
        super(userInput);

        if (newInput) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(timeline, formatter);
            this.timeline = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            this.dateAndTime = date;
        } else {
            this.timeline = timeline;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(timeline, formatter);
            this.dateAndTime = date;
        }
    }

    /**
     * checks whether the task is schedule on the given date.
     * @param comparisonDate the date that's giveen by the user in the form of YYYY-MM-DD
     * @return boolean indicating if the task is on the given date
     */
    public boolean isDate(String comparisonDate) {
        LocalDate comparison = LocalDate.parse(comparisonDate);
        LocalDate deadlineDate = this.dateAndTime.toLocalDate();

        return comparison.isEqual(deadlineDate);
    }

    public LocalDateTime getLocalDateTime() {
        return this.dateAndTime;
    }

    /**
     * printName() method returns the name and the task indicator of the Event object.
     *
     * @return a string representation of a Event object
     */
    public String printName() {

        return taskIndicator + " " + super.printName() + " (at: " + this.timeline + ")";
    }

}


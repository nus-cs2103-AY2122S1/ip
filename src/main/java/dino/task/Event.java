package dino.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that start at a specific time and ends at a specific time
 * e.g., team project meeting on 2/10/2019 2-4pm
 * It is a subclass of Task
 */
public class Event extends Task{

    private LocalDate at;

    /**
     * Constructs an Event object
     *
     * @param description the description for the event task
     * @param at the time at which the event occurs
     */
    public Event(String description, LocalDate at){
        super(description);
        this.at = at;
    }

    /**
     * Displays the event task in the format of "E | Status | Description | Time"
     * The time is displayed in the format of "MMM dd yyyy"
     *
     * @return the event task in the required format
     */
    @Override
    public String toString() {
        return "E" + super.toString() + " | "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

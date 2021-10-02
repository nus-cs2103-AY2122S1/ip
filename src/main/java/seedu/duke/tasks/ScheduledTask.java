package seedu.duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Is a subclass of {@code Task} which emphasises in completing this
 * {@code ScheduledTask} in a specific timeslot.
 */
public class ScheduledTask extends Task {
    private final LocalDate taskDate;
    private final String date;
    private final int timeFrom;
    private final int timeTo;

    /**
     * Primary constructor for this class.
     * 
     * @param description is the description of this {@code ScheduledTask}.
     * @param date        is the date which this {@code ScheduledTask} will be held
     *                    on.
     * @param timeFrom    is the start time of this {@code ScheduledTask}.
     * @param timeTo      is the end time for this {@code ScheduledTask}.
     */
    public ScheduledTask(String description, String date, int timeFrom, int timeTo) {
        super(description);

        this.taskDate = LocalDate.of(Integer.parseInt(date.split("-")[2]), Integer.parseInt(date.split("-")[1]),
                Integer.parseInt(date.split("-")[0]));
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.date = date;
    }

    private ScheduledTask(String description, LocalDate localDate, int timeFrom, int timeTo, boolean isDone,
            ArrayList<String> tags) {
        super(description, isDone, tags);
        this.taskDate = localDate;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.date = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Gets the date of this {@code ScheduledTask}.
     * 
     * @return a {@code LocalDate} of which this {@code ScheduledTask} will be held
     *         on.
     */
    public LocalDate getTaskDate() {
        return this.taskDate;
    }

    /**
     * Gets the start time of this {@code ScheduledTask}.
     * 
     * @return the start time of this {@code ScheduledTask}.
     */
    public int getTimeFrom() {
        return this.timeFrom;
    }

    /**
     * Gets end time of this {@code ScheduledTask}.
     * 
     * @return the end time of this {@code ScheduledTask}.
     */
    public int getTimeTo() {
        return this.timeTo;
    }

    /**
     * Gets the date of this {@code ScheduledTask} in a form of {@code String}.
     * 
     * @return the date of this {@code ScheduledTask} in a form of {@code String}.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Gets the symbol of this {@code ScheduledTask} object. The symbol for
     * {@code ScheduledTask} object is "ST".
     * 
     * @return "ST"
     */
    @Override
    public String getSymbol() {
        return "ST";
    }

    /**
     * Marks the current {@code ScheduledTask} as done.
     * 
     * @return a new {@code ScheduledTask} object with the same description, but
     *         setting {@code isDone} property to be true.
     */
    @Override
    public ScheduledTask markAsDone() {
        return new ScheduledTask(this.getDescription(), this.getTaskDate(), this.getTimeFrom(), this.getTimeTo(), true,
                this.getTags());
    }

    /**
     * Describes the current {@code ScheduledTask} object.
     * 
     * @return a description of the current {@code ScheduledTask} object.
     */
    @Override
    public String toString() {
        return "[ST][" + this.getStatusIcon() + "] " + this.getDescription() + " on " + this.getDate() + " at "
                + this.getTimeFrom() + " to " + this.getTimeTo();
    }

}

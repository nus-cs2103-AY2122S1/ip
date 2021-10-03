package seedu.duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Is a subclass of {@code Task} which emphasises in completing this
 * {@code PeriodTask} between a certain date.
 */
public class PeriodTask extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Primary constructor for this class.
     * 
     * @param description is the description of this {@code PeriodTask}.
     * @param from        is the start date which this {@code PeriodTask} can be
     *                    completed.
     * @param to          is the end date which this {@code PeriodTask} can be
     *                    completed.
     */
    public PeriodTask(String description, String from, String to) {
        super(description);
        this.from = getLocalDateFromString(from);
        this.to = getLocalDateFromString(to);
    }

    public PeriodTask(String description, String from, String to, ArrayList<String> tags) {
        super(description, false, tags);
        this.from = getLocalDateFromString(from);
        this.to = getLocalDateFromString(to);
    }

    private PeriodTask(String description, LocalDate from, LocalDate to, boolean isDone, ArrayList<String> tags) {
        super(description, isDone, tags);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start date of this {@code PeriodTask} in a form of {@code String}.
     * 
     * @return the start date of this {@code PeriodTask} in a form of
     *         {@code String}.
     */
    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Gets the end date of this {@code PeriodTask} in a form of {@code String}.
     * 
     * @return the end date of this {@code PeriodTask} in a form of {@code String}.
     */
    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Gets the symbol of this {@code PeriodTask} object. The symbol for
     * {@code PeriodTask} object is "PT".
     * 
     * @return "PT"
     */
    @Override
    public String getSymbol() {
        return "PT";
    }

    /**
     * Marks the current {@code PeriodTask} as done.
     * 
     * @return a new {@code PeriodTask} object with the same description, but
     *         setting {@code isDone} property to be true.
     */
    @Override
    public PeriodTask markAsDone() {
        return new PeriodTask(this.getDescription(), this.from, this.to, true, this.getTags());
    }

    /**
     * Describes the current {@code PeriodTask} object.
     * 
     * @return a description of the current {@code PeriodTask} object.
     */
    @Override
    public String toString() {
        String str = "[PT][" + this.getStatusIcon() + "] " + this.getDescription() + " between " + this.getFrom()
                + " and " + this.getTo();
        str = this.addTagsToString(str);
        return str;
    }

    private LocalDate getLocalDateFromString(String date) {
        return LocalDate.of(Integer.parseInt(date.split("-")[2]), Integer.parseInt(date.split("-")[1]),
                Integer.parseInt(date.split("-")[0]));
    }
}

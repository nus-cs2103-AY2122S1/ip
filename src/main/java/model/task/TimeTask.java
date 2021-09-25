package model.task;

import java.time.LocalDate;
import java.util.Objects;

import parser.Parser;

/**
 * The abstract for models.task which has association with time.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.01
 */
public abstract class TimeTask extends Task {
    /** LocalDate time of the timeTask */
    private final LocalDate time;

    /**
     * Adapted constructor for TimeTask.
     * isDone is set to false.
     *
     * @param description the description of the TimeTask.
     * @param timeString  the time of the TimeTask with yyyy-MM-dd format.
     */
    public TimeTask(String description, String timeString) {
        super(description);
        this.time = Parser.parseTimeString(timeString);
    }

    /**
     * Adapted constructor for TimeTask.
     *
     * @param description the description of the TimeTask.
     * @param isDone      whether the models.task is done.
     * @param timeString  the time of the TimeTask with yyyy-MM-dd format.
     */
    public TimeTask(String description, boolean isDone, String timeString) {
        super(description, isDone);
        this.time = Parser.parseTimeString(timeString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), time);
    }

    /**
     * Check whether the given object is the same object, is of type TimeTask and have matching description and time.
     *
     * @param other other object to be checked
     * @return whether that object should equal to this time task or not
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimeTask)) {
            return false;
        }
        TimeTask otherTimeTask = (TimeTask) other;
        return otherTimeTask.getDescription().equals(getDescription())
            && otherTimeTask.getTime().equals(time);
    }

    /**
     * Getter for time.
     *
     * @return LocalDate object of time.
     */
    public LocalDate getTime() {
        return this.time;
    }
}

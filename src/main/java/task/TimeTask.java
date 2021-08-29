package task;

import parser.Parser;

import java.time.LocalDate;

/**
 * The abstract for task which has association with time.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.01
 * @since 0.00
 */
public abstract class TimeTask extends Task {
    /** LocalDate time of the timeTask */
    private LocalDate time;

    /**
     * Default constructor for TimeTask.
     * isDone is set to false and time is set to null.
     *
     * @param description the description of the TimeTask
     */
    public TimeTask(String description) {
        super(description);
    }

    /**
     * Default constructor for TimeTask.
     * time is set to null.
     *
     * @param description the description of the TimeTask
     * @param isDone      whether the task is done
     */
    public TimeTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Default constructor for TimeTask.
     *
     * @param description the description of the TimeTask
     * @param isDone      whether the task is done
     * @param time        the LocalDate time for the TimeTask
     */
    public TimeTask(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Adapted constructor for TimeTask.
     * isDone is set to false.
     *
     * @param description the description of the TimeTask
     * @param timeString  the time of the TimeTask with yyyy-MM-dd format
     */
    public TimeTask(String description, String timeString) {
        super(description);
        this.time = Parser.parseTimeString(timeString);
    }

    /**
     * Adapted constructor for TimeTask.
     *
     * @param description the description of the TimeTask
     * @param isDone      whether the task is done
     * @param timeString  the time of the TimeTask with yyyy-MM-dd format
     */
    public TimeTask(String description, boolean isDone, String timeString) {
        super(description, isDone);
        this.time = Parser.parseTimeString(timeString);
    }

    /**
     * Getter for time.
     *
     * @return LocalDate object of time
     */
    public LocalDate getTime() {
        return this.time;
    }

    /**
     * Setter for time.
     *
     * @param time the LocalDate time for the TimeTask to be set to
     */
    public void setTime(LocalDate time) {
        this.time = time;
    }
}

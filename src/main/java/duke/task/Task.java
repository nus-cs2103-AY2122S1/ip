package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstract Class to manage task stored in chatbot.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
 */
public abstract class Task {
    /** Date format. */
    private static final String DATETIME = "dd/MM/yy HHmm";
    private static final SimpleDateFormat formatDateTime = new SimpleDateFormat(DATETIME);

    /** Stores the task. */
    private String task;
    /** Stores if the task is done. */
    private boolean isDone;
    /** Stores the date time for the task. */
    private Date dateTime;

    /**
     * Constructor for duke.task.Task.
     *
     * @param task task to be stored.
     */
    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Constructor for duke.task.Task.
     *
     * @param task duke.task.Task to be stored.
     * @param isDone Whether the task is done.
     */
    Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
        this.dateTime = null;
    }

    /**
     * Constructor for duke.task.Task.
     *
     * @param task duke.task.Task to be stored.
     * @param dateTime Date / Time of the task.
     * @param isDone Whether the task is done.
     */
    Task(String task, String dateTime, boolean isDone) throws ParseException {
        this.task = task;
        this.isDone = isDone;
        this.dateTime = formatDateTime.parse(dateTime);
    }

    /**
     * Constructor for duke.task.Task.
     *
     * @param task duke.task.Task to be stored.
     * @param dateTime Date / Time of the task.
     * @throws ParseException Date / Time format is invalid.
     */
    Task(String task, String dateTime) throws ParseException {
        this.task = task;
        this.isDone = false;
        this.dateTime = formatDateTime.parse(dateTime);
    }

    /**
     * Marks the task as done.
     * If task is successfully marked as done, true will be returned.
     * If task is not successfully marked as done, false will be returned instead.
     *
     * @return if update is successful.
     */
    public boolean markDone() {
        if (isDone) {
            return false;
        }
        isDone = true;
        return true;
    }

    /**
     * Returns an output to save to the txt file.
     * Format is as follow: {@literal <}Type(T/D/E){@literal >} | {@literal <}Description{@literal >} |
     * {@literal <}Done{@literal >} | {@literal <}DateTime if applicable{@literal >}.
     *
     * @return string to save the txt file
     */
    public abstract String saveOutput();


    public boolean getIsDone() {
        return isDone;
    }

    public String getTask() {
        return task;
    }

    public String getDateTime() {
        return formatDateTime.format(dateTime);
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + task.toString();
    }
}

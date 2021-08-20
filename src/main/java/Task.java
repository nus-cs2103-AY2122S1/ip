import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstract Class to manage task stored in chatbot.
 *
 * @author marcuspeh
 * @version Level-8
 * @since 20 Aug 2021
 */
public abstract class Task {
    /** Stores the task. */
    private String task;
    /** Stores if the task is done. */
    private boolean done;
    /** Stores the date time for the task */
    private Date dateTime;
    /** Date format */
    private static final String DATETIME = "dd/MM/yy HHmm";
    private static final SimpleDateFormat formatDateTime = new SimpleDateFormat(DATETIME);

    /**
     * Constructor for Task.
     *
     * @param task task to be stored
     */
    Task(String task) {
        this.task = task;
        this.done = false;
        this.dateTime = null;
    }

    /**
     * Constructor for Task.
     *
     * @param task Task to be stored
     * @param dateTime Date / Time of the task.
     * @throws ParseException Date / Time format is invalid.
     */
    Task(String task, String dateTime) throws ParseException {
        this.task = task;
        this.done = false;
        this.dateTime = formatDateTime.parse(dateTime);
    }

    /**
     * Marks the task as done.
     * If task is successfully marked as done, true will be returned.
     * If task is not successfully marked as done, fasle will be returned instead.
     *
     * @return if update is successful.
     */
    public boolean markDone() {
        if (done)
            return false;
        done = true;
        return true;
    }

    /** Checks if task is done or not. */
    public boolean isDone() {
        return done;
    }

    /**
     * Getter for task.
     *
     * @return task
     */
    public String getTask() {
        return task;
    }

    /** Getter for dateTime.
     *
     * @return dateTime
     */
    public String getDateTime() {
        return formatDateTime.format(dateTime);
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + task.toString();
    }
}

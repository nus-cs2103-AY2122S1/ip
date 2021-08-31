/**
 * @author Hang Zelin
 * <p>
 * Events class that extends Task class. It is one of the types in 3 tasks.
 * Will contain a time in the form of "/by"
 */
package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Events extends Task {

    private boolean isDone;
    private String task;
    private LocalDateTime time;
    private String taskType = "E";

    /**
     * Constructor for Events containing boolean value if the task is done, the
     * specific task info, and the time for the task.
     *
     * @param isDone
     * @param task
     * @param time
     */
    public Events(boolean isDone, String task, LocalDateTime time) {
        this.isDone = isDone;
        this.task = task;
        this.time = time;
    }

    /**
     * Returns the task info in the format of "[type][] task info (/by ...)"
     *
     * @return Task info parsed in the format duke executes.
     */
    @Override
    public String getTaskInfo() {
        String doneStr = "";
        if (!this.isDone) {
            doneStr = " ";
        } else {
            doneStr = "X";
        }

        return "[" + taskType + "]" + "[" + doneStr + "] " + task + " (at: " + parsedTime() + ")";
    }

    /**
     * Returns the Parsed time info in the format of "MMM dd yyyy HH:mm"
     * Noted: This method is only applicable for "event" and "deadline" type task.
     *
     * @return Return the parsed time in the format duke can understand.
     */
    @Override
    public String parsedTime() {
        String parsedTime = "";
        if (this.time != null) {
            parsedTime = this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH));
        } else {
            parsedTime = "I don't know the time.";
        }

        return parsedTime;
    }

    /**
     * Returns the Parsed time info in the format of "dd/mm/yy hhmm".
     * Noted: This method is only applicable for "event" and "deadline" type task,
     *
     * @return Time in the format of "dd/mm/yy hhmm" which duke can understand.
     */
    @Override
    public String getTime() {
        if (this.time == null) {
            return "I don't know the time";
        }
        return this.time.getDayOfMonth() + "/" + this.time.getMonthValue() + "/" + this.time.getYear() + " "
                + ((this.time.getHour() < 10) ? "0" + this.time.getHour()
                        : this.time.getHour())
                + ((this.time.getMinute() < 10) ? "0" + this.time.getMinute()
                : this.time.getMinute());
    }


    /**
     * Returns the task info in the format of save data requirement,
     * that is: "taskType | done or not | task info | time".
     *
     * @return Task info in the format of "taskType | done or not | task info | time".
     */
    @Override
    public String getSaveDataInfo() {
        return this.taskType + " | " + (this.isDone ? 1 : 0) + " | " + task + " | " + getTime();
    }

    /**
     * Mark this task as done.
     */
    @Override
    public void markDone() {
        this.isDone = true;
    }


}

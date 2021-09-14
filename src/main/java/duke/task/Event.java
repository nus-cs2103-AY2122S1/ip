package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
/**
 * @@author Hang Zelin
 *
 * Events class that extends Task class. It is one of the types in 3 tasks.
 * Will contain a time in the form of "/at".
 */
public class Event extends Task {
    private static final String TASKTYPE = "E";
    private boolean isDone;
    private final String task;
    private final LocalDateTime time;

    /**
     * Constructor for Events containing boolean value if the task is done, the
     * specific task info, and the time for the task.
     *
     * @param isDone Indicates if the task is done or not.
     * @param task Specific task info.
     * @param time Specific time info.
     */
    public Event(boolean isDone, String task, LocalDateTime time) {
        this.isDone = isDone;
        this.task = task;
        this.time = time;
    }

    /**
     * Returns the task info in the format of "[type][] task info (/at ...)".
     *
     * @return Task info parsed in the format duke executes.
     */
    @Override
    public String getTaskStatus() {
        String doneStatus;
        String taskStatus;
        if (!this.isDone) {
            doneStatus = " ";
        } else {
            doneStatus = "X";
        }

        taskStatus = "[" + TASKTYPE + "]" + "[" + doneStatus + "] " + task + " (at: " + parsedTime() + ")";
        return taskStatus;
    }

    /**
     * Returns the Parsed time info in the format of "MMM dd yyyy HH:mm"
     * Note: This method is only applicable for "event" and "deadline" type task.
     *
     * @return Return the parsed time in the format duke can understand.
     */
    @Override
    public String parsedTime() {
        String parsedTime;
        if (this.time != null) {
            parsedTime = this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH));
        } else {
            parsedTime = "I don't know the time.";
        }

        return parsedTime;
    }

    private String getUnknownTime() {
        String specificTime;
        specificTime = "I don't know the time";
        return specificTime;
    }

    private String getDate() {
        String specificDate;
        specificDate = this.time.getDayOfMonth() + "/"
                + this.time.getMonthValue() + "/" + this.time.getYear() + " ";
        return specificDate;
    }

    private String getHour() {
        String specificTime;
        if (this.time.getHour() < 10) {
            specificTime = "0" + this.time.getHour();
        } else {
            specificTime = "" + this.time.getHour();
        }
        return specificTime;
    }

    private String getMinute() {
        String specificTime;
        if (this.time.getMinute() < 10) {
            specificTime = "0" + this.time.getMinute();
        } else {
            specificTime = "" + this.time.getMinute();
        }
        return specificTime;

    }

    /**
     * Returns the Parsed time info in the format of "dd/mm/yy hhmm".
     * Note: This method is only applicable for "event" and "deadline" type task,
     *
     * @return Time in the format of "dd/mm/yy hhmm" which duke can understand.
     */
    @Override
    public String getTimeForSaveData() {
        String saveDataTime = "";
        String specificDate;
        String specificTime;

        if (this.time == null) {
            saveDataTime = getUnknownTime();
            return saveDataTime;
        }

        specificDate = getDate();

        specificTime = getHour() + getMinute();

        saveDataTime += specificDate + specificTime;
        return saveDataTime;
    }


    /**
     * Returns the task info in the format of save data requirement,
     * that is: "taskType | done or not | task info | time".
     *
     * @return Task info in the format of "taskType | done or not | task info | time".
     */
    @Override
    public String getSaveDataInfo() {
        String dataInfo;
        int value;
        if (this.isDone) {
            value = 1;
        } else {
            value = 0;
        }
        dataInfo = TASKTYPE + " | " + value + " | " + task + " | " + getTimeForSaveData();
        return dataInfo;
    }

    /**
     * Marks this task as done.
     */
    @Override
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Resets this task as undone.
     */
    @Override
    public void resetDone() {
        this.isDone = false;
    }


}

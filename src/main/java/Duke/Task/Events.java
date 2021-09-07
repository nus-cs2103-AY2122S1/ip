package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @@author Hang Zelin
 *
 * Events class that extends Task class. It is one of the types in 3 tasks.
 * Will contain a time in the form of "/by"
 */
public class Events extends Task {

    private boolean isDone;
    private String task;
    private LocalDateTime time;
    private static final String TASKTYPE = "E";

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
            return "I don't know the time";
        }

        specificDate = this.time.getDayOfMonth() + "/" + this.time.getMonthValue() + "/" + this.time.getYear() + " ";
        if (this.time.getHour() < 10) {
            specificTime = "0" + this.time.getHour();
        } else {
            specificTime = "" + this.time.getHour();
        }

        if (this.time.getMinute() < 10) {
            specificTime += "0" + this.time.getMinute();
        } else {
            specificTime += this.time.getMinute();
        }

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
        int value = 0;
        if (this.isDone) {
            value = 1;
        } else {
            value = 0;
        }
        dataInfo =  this.TASKTYPE + " | " + value + " | " + task + " | " + getTimeForSaveData();
        return dataInfo;
    }

    /**
     * Mark this task as done.
     */
    @Override
    public void markDone() {
        this.isDone = true;
    }


}

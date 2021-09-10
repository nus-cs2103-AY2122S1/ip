package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Packaged command that the parser outputs. Is process by the logic layer and is for ease of processing.
 */
public class Command {

    private Task.TaskType taskType;
    private ArrayList<String> listOfCommandInputs;
    private String log;
    private LocalDateTime localDateTime;


    /**
     * Constructor for packaged command made by parser
     * @param taskType duke.Task type
     * @param listOfCommandInputs The entire input entered by the user
     * @param log The logged statement. Used to update log since there are keywords that are not supposed to
     *            appear in the log statement
     */
    public Command(Task.TaskType taskType, ArrayList<String> listOfCommandInputs, String log) {
        this.taskType = taskType;
        this.listOfCommandInputs = listOfCommandInputs;
        this.log = log;
    }

    /**
     * Overloaded constructor for Command class that includes local dateTime
     * @param taskType See above
     * @param listOfCommandInputs See above
     * @param log See above
     * @param localDateTime Includes the localDateTime for command
     */
    public Command(Task.TaskType taskType, ArrayList<String> listOfCommandInputs,
                   String log, LocalDateTime localDateTime) {
        this(taskType, listOfCommandInputs, log);
        this.localDateTime = localDateTime;
    }


    /**
     * Returns tasktype
     * @return duke.Task type. Made task type into an enum for ease
     */
    public Task.TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * Returns list of command inputs
     * @return ArrayList of strings, split by " ". Makes it easier to access individual words
     */
    public ArrayList<String> getListOfCommandInputs() {
        return this.listOfCommandInputs;
    }

    /**
     * Returns log statement
     * @return String for logging later
     */
    public String getLog() {
        return this.log;
    }

    /**
     * Returns local dateTime
     * @return localdatetime.
     */
    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }

}

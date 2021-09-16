package taskman.tasktypes;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import taskman.exception.DukeException;
import taskman.exception.EmptyDescriptionException;
import taskman.exception.EmptyTimeException;
import taskman.exception.InvalidDateException;
import taskman.exception.InvalidTimeException;

/**
 * Task class that sets description of task, date, time
 */
public class Task {
    protected String taskType;
    protected boolean isDone;
    protected String description;
    protected LocalDate date;
    protected int time;

    // Constructors
    /**
     * Basic constructor for task (used during subclass instance)
     */
    public Task(boolean isDone) {
        setState(isDone);
    }

    /**
     * Empty task constructor
     * @return Task empty task
     */
    public static Task empty(){
        return new Task(false);
    }

    /**
     * Set the task details based on a list of task details
     *
     * @param type type of task
     * @param taskDetails task details in a form of a list
     * @throws DukeException
     */
    protected void setTaskDetails(String type, List<String> taskDetails) throws DukeException {
        setTaskType(type);
        setDescription(retrieveTaskDescription(taskDetails));
        setDateTime(retrieveDateTimeDetails(taskDetails));
    }

    /**
     * Sets the date and time based on a single string that contains date and time
     *
     * @param input
     * @throws DukeException
     */
    protected void setDateTime(String input) throws DukeException {
        if (input == null){
            this.date = null;
            this.time = -1;
            return;
        }

        try {
            String[] formattedDateTime = formatDateTime(input);
            setDate(formattedDateTime);
            setTime(formattedDateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Wrong date format(use YYYY-MM-DD)");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyTimeException("Missing time");
        }
    }


    /**
     * Retrieves task description from the formatted user input
     *
     * @param formattedInput
     * @return
     * @throws DukeException
     */
    private String retrieveTaskDescription(List<String> formattedInput) throws DukeException {
        String taskDetails = formattedInput.get(0);
        if (taskDetails.equals("")){
            throw new EmptyDescriptionException("\nMissing description");
        }
        return taskDetails;
    }

    /**
     * Retrieves date and time from the formatted user input
     *
     * @param formattedInput
     * @return
     */
    private String retrieveDateTimeDetails(List<String> formattedInput) {
        return formattedInput.get(1);
    }

    /**
     * Seperates string into date and time
     *
     * @param input user input
     * @return
     */
    private String[] formatDateTime(String input) {
        return input.trim().split(" ");
    }

    /**
     * Ensures that the given time is in 24 hour format
     *
     * @param hoursMins hours and mins
     * @throws InvalidTimeException
     */
    private void check24HourFormat (int hoursMins) throws InvalidTimeException {
        if (!(hoursMins <2400 && hoursMins > 0 && hoursMins%100 <60)) {
            throw new InvalidTimeException("Invalid time format (use 24hr format)");
        }
    }

    /**
     * Returns a string to be displayed to user
     *
     * @return String containing details of the task
     */
    @Override
    public String toString() {
        String state = isDone ? "[X] " : "[ ] ";
        String eventType = "[" + this.taskType + "]";
        return eventType + state + this.description;
    }

    /**
     * Returns a string that describes the instance for saving
     *
     * @return String containing details of the task
     */
    public String saveTaskTxt() {
        String state = isDone ? "T" : "F";
        return taskType + " " + state + " " + description;
    }

    /**
     * Returns a string that describes the instance for saving in  a CSV file
     *
     * @return String containing details of the task
     */
    public String saveTaskCsv() {
        String state = isDone ? "T" : "F";
        return taskType + "," + state + "," + description;
    }

    // Setter and getters
    private void setState(boolean isDone) {
        this.isDone = isDone;
    }
    public Task setDone(){
        this.isDone = true;
        return this;
    }
    private void setDate(String[] formattedDateTime) throws DateTimeParseException {
        this.date = LocalDate.parse(formattedDateTime[0]);
    }
    private void setTime(String[] formattedDateTime) throws InvalidTimeException {
        int hoursMins = Integer.parseInt(formattedDateTime[1]);
        check24HourFormat(hoursMins);
        this.time = hoursMins;
    }
    private void setTaskType(String input){
        this.taskType = input;
    }
    protected void setDescription(String input){
        this.description = input;
    }
    protected String getSaveDate() {
        return this.date.toString() + " " + this.time;
    }
    protected String getSaveDateCsv() {
        return this.date.toString() + "," + this.time;
    }
    protected String getFormatDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time + " hrs";
    }
    public String getDescription(){
        return this.description;
    }
}


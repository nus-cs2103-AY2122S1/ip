package duke.taskTypes;

import duke.exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

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



    // Reformats user input before setting Task details methods
        // taskDetails refers to taskType, description, date and time
    protected void setTaskDetails(String type, List<String> formattedInput) throws DukeException {
        setTaskType(type);
        setDescription(retrieveTaskDescription(formattedInput));
        setDateTime(retrieveDateTimeDetails(formattedInput));
    }
    private void setTaskType(String input){
        this.taskType = input;
    }
    protected void setDescription(String input){
        this.description = input;
    }
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
    private void setDate(String[] formattedDateTime) throws DateTimeParseException {
        this.date = LocalDate.parse(formattedDateTime[0]);
    }
    private void setTime(String[] formattedDateTime) throws InvalidTimeException {
        int hoursMins = Integer.parseInt(formattedDateTime[1]);
        check24HourFormat(hoursMins);
        this.time = hoursMins;
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

    // Setter and getters
    private void setState(boolean isDone) {
        this.isDone = isDone;
    }
    public Task setDone(){
        this.isDone = true;
        return this;
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

    // methods that returns formatted string for saving / displaying
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
    public String saveTask() {
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
}


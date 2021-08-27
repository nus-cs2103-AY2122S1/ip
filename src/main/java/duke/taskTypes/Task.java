package duke.taskTypes;

import duke.exception.DukeException;
import duke.exception.EmptyTimeException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidTimeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task class that sets description of task, date, time
 */
public class Task {
    protected String eventType;
    protected boolean isDone;
    protected String description;
    protected LocalDate date;
    protected int time;

    /**
     * Basic constructor for task (used during subclass instance)
     */
    public Task(boolean isDone){
        setState(isDone);
    }

    /**
     * Empty task constructor
     * @return Task empty task
     */
    public static Task empty(){
        return new Task(false);
    }

    private void setState(boolean isDone) {
        this.isDone = isDone;
    }

    protected void setDescription(String input){
        this.description = input;
    }

    protected void setDate(String input) throws DukeException {
        if (input == null){
            this.date = null;
            this.time = -1;
        } else {
            try {
                String[] timeFormat = input.trim().split(" ");
                this.date = LocalDate.parse(timeFormat[0]);
                int hoursMins = Integer.parseInt(timeFormat[1]);
                if (hoursMins <2401 && hoursMins > 999 && hoursMins%100 <60) {
                    this.time = hoursMins;
                } else {
                    throw new InvalidTimeException("Invalid time format (use 24hr format)");
                }
            } catch (DateTimeParseException e) {
                throw new InvalidDateException("Wrong date format(use YYYY-MM-DD)");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyTimeException("Missing time");
            }
        }
    }

    protected void setEventType(String input){
        this.eventType = input;
    }

    public Task setDone(){
        this.isDone = true;
        return this;
    }

    protected String getDate() {
        return this.date.toString() + " " + this.time;
    }

    protected String getFormatDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time;
    }

    public String getDescription(){
        return this.description;
    }

    /**
     * Returns a string that describes the instance for display
     * @return String containing details of the task
     */
    @Override
    public String toString() {
        String state = isDone ? "[X] " : "[ ] ";
        String eventType = "[" + this.eventType + "]";
        return eventType + state + this.description;
    }

    /**
     * Returns a string that describes the instance for saving
     * @return String containing details of the task
     */
    public String saveTask() {
        String state = isDone ? "T" : "F";
        return eventType + " " + state + " " + description;
    }
}


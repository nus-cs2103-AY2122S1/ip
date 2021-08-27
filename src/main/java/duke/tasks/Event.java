package main.java.duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected String prefix;
    protected String time;
    protected LocalDateTime timeFormatted;

    /**
     * Constructs a new event task.
     * 
     * @param name name of the task
     * @param time date of the task
     */
    public Event(String name, String time) {
        super(name);
        this.prefix = "[E]";
        if (canBeFormattedDateTime(time)) {
            this.time = time;
            this.timeFormatted = fromStringToDateTime(time);
        } else {
            this.time = time;
        }
    }

    public LocalDateTime getTimeFormatted() {
        return this.timeFormatted;
    }

    private boolean canBeFormattedDateTime(String time) {
        try {
            LocalDateTime.parse(time.substring(1), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    
    }

    /**
     * Converts a string to date time format.
     * 
     * @param time the given time string
     * @return the time in LocalDateTime format
     */
    public LocalDateTime fromStringToDateTime(String time) {
        return LocalDateTime.parse(time.substring(1), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    /**
     * show the prefix of the event
     * 
     * @return the prefix 
     */
    @Override
    public String showPrefix() {
        return this.prefix;
    }

    /**
     * print out the relevant info of the event
     */
    @Override
    public void showThisTask() {
        if (canBeFormattedDateTime(this.time)) {
            System.out.println(this.prefix + super.showStatus() + this.name + "(at:" + this.timeFormatted + ")");
        } else {
            System.out.println(this.prefix + super.showStatus() + this.name + "(at:" + this.time + ")");
        }
    }

    /**
     * mark an event as done and print out relevant information.
     */
    @Override
    public void markAsDone() {
        this.hasDone = true;
    }

    @Override
    public String toString() {
        if (this.timeFormatted != null) {
            return (this.prefix + " " + super.showStatus() + this.name + ":" + this.timeFormatted);
        } else {
            return (this.prefix + " " + super.showStatus() + this.name + ":" + this.time);
        }
        
    }
}
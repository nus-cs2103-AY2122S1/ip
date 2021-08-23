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
     * @param date date of the task
     */
    public Event(String name, String time) {
        super(name);
        this.prefix = "[E]";
        if (canBeFormattedDateTime(time)) {
            this.timeFormatted = fromStringToDateTime(time);
        } else {
            this.time = time;
        }
    }

    private boolean canBeFormattedDateTime(String time) {
        try {
            System.out.println(time);
            LocalDateTime.parse(time.substring(1), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    
    }

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

    @Override
    public String toString() {
        return (this.prefix + " " + super.showStatus() + this.name + ":" + this.time);
    }

    /**
     * print out the relevant info of the event
     */
    @Override
    public void showThisTask() {
        if (this.time == null) {
            System.out.println(this.prefix + super.showStatus() + this.name + "(at:" + this.timeFormatted.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ")");
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
        System.out.println(this.prefix + "[X] " + this.name + "(by:" + this.time + ")");
    }
}

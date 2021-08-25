package duke.tasks;
// import java.sql.Date;
// import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected String prefix;
    protected String date;
    protected LocalDate dateFormatted;

    /**
     * Constructs a new deadline task.
     * 
     * @param name name of the task
     * @param date date of the task
     */
    public Deadline(String name, String date) {
        super(name);
        this.prefix = "[D]";
        if (canBeFormattedDate(date)) {
            this.date = date;
            this.dateFormatted = fromStringToDate(date.substring(1));
        } else {
            this.date = date;
        }
    }

    private boolean canBeFormattedDate(String date) {
        try {
            LocalDate.parse(date.substring(1), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    
    }

    public LocalDate getDateFormatted() {
        return this.dateFormatted;
    }

    /**
     * Converts a date string to local date format
     * 
     * @param dateString
     * @return a local date of the string
     */
    public LocalDate fromStringToDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    /**
     * show the prefix of the deadline
     * 
     * @return the prefix 
     */
    @Override
    public String showPrefix() {
        return this.prefix;
    }

    @Override
    public String toString() {
        return (this.prefix + " " + super.showStatus() + this.name + ":" + this.dateFormatted);
    }

    /**
     * print out the relevant info of the deadline task
     */
    @Override
    public void showThisTask() {
        if (canBeFormattedDate(this.date)) {
            System.out.println(this.prefix + super.showStatus() + this.name + "(by:" + this.dateFormatted.format(DateTimeFormatter.ofPattern("MMM d uuuu")) + ")");
        } else {
            System.out.println(this.prefix + super.showStatus() + this.name + "(by:" + this.date + ")");
        }
        
    }

    /**
     * mark a deadline task as done and print out relevant information.
     */
    @Override
    public void markAsDone() {
        this.hasDone = true;
    }
}

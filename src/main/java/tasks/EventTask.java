package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The EventTask Class inherits Task Class
 * to support different specificities of a task
 * as per input by user.
 */
public final class EventTask extends Task {

    /** Stores the type of task information that identifies a EventTask */
    private final String taskType = "[E]";

    /** Stores the date information of an Event task */
    private String date;

    /** Stores the date information as a LocalDate */
    private LocalDate localDate;

    /**
     * Constructs an Event task and check if a date can be
     * stored as LocalDate. If so, save it, otherwise save
     * date as per user input.
     *
     * @param description the input string to describe the Event task
     * @param date the date of the event
     */
    public EventTask(String description, String date) {
        super(description);
        LocalDate day = getDate(date);
        this.date = date;
        setLocalDate(day);
    }

    private LocalDate getDate(String userInput) {
        String str = userInput.replaceAll(" ", "");
        String temp = "[0-9]{1,2}[\\\\/][0-9]{1,2}[\\\\/][0-9]{4}";
        Pattern p = Pattern.compile(temp);
        Matcher m = p.matcher(str);
        String dateStr;
        if (m.find()) {
            dateStr = m.group();
            try {
                String[] date = dateStr.split("/");
                int day = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                return LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Retrieves the information of the type of task.
     *
     * @return the String description of the type of task
     */
    public String getType() {
        return this.taskType;
    }

    /**
     * Sets the date as a valid date.
     * @param localDate the specific valid date to store
     */
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Changes stored date to a new date.
     * @param newDate date to be changed to
     */
    public void updateDate(String newDate) {
        this.date = newDate;
    }

    /**
     * Retrieves the information on the date associated with task.
     *
     * @return the date associated with task in as LocalDate
     */
    @Override
    public LocalDate getLocalDate() {
        return this.localDate;
    }

    /**
     * Retrieves the description specific to the Event task
     * and use a LocalDate if available.
     *
     * @return the description specific to the Event task
     */
    @Override
    public String getDescription() {
        if (this.localDate == null) {
            return super.getDescription() + " " + "(at: " + this.date + ")";
        } else {
            return super.getDescription() + " " + "(at: " + this.localDate.getDayOfMonth() + " "
                    + Month.of(this.localDate.getMonthValue()) + " " + this.localDate.getYear() + ")";
        }
    }

    /**
     * Retrieves the simple description of the task.
     *
     * @return the simple description of the task
     */
    public String getSimpleTaskDescription() {
        return super.getDescription();
    }

    /**
     * Retrieves the information on the format of the task to be saved.
     *
     * @return the String representation of how the task will be saved
     */
    @Override
    public String getSaveFormat() {
        if (getStatus().equals("[ ]")) {
            return "E" + "|" + getSimpleTaskDescription().strip() + "|" + this.date + "|" + 0;
        } else {
            return "E" + "|" + getSimpleTaskDescription().strip() + "|" + this.date + "|" + 1;
        }
    }
}

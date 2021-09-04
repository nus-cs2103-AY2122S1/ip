package tasks;

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
    private final String date;

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
        this.date = date;
        String day = checkForDate(date);
        if (!day.equals("")) {
            LocalDate ld = convertDate(day);
            setLocalDate(ld);
        }
    }

    private String checkForDate(String userInput) {
        String temp = "^[0-9]{1,2}[\\\\/][0-9]{1,2}[\\\\/][0-9]{4}\\s[0-9]{4}$";
        Pattern p = Pattern.compile(temp);
        Matcher m = p.matcher(userInput);
        if (m.find()) {
            return m.group();
        }
        return "";
    }

    private LocalDate convertDate(String input) {
        String[] date = input.substring(0, input.length() - 4).split("/");
        int day = Integer.parseInt(date[0].replaceAll(" ", ""));
        int month = Integer.parseInt(date[1].replaceAll(" ", ""));
        int year = Integer.parseInt(date[2].replaceAll(" ", ""));
        return LocalDate.of(year, month, day);
    }

    /**
     * Retrieves the information of the type of task.
     *
     * @return the String description of the type of task
     */
    public String getType() {
        return this.taskType;
    }

    private void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
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
        if (super.getStatus().equals("[ ]")) {
            return "E" + "|" + this.getSimpleTaskDescription().strip() + "|" + this.date + "|" + 0;
        } else {
            return "E" + "|" + this.getSimpleTaskDescription().strip() + "|" + this.date + "|" + 1;
        }
    }
}

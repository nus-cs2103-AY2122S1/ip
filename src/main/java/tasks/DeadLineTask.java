package tasks;

import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The DeadlineTask Class inherits Task Class
 * to support different specificities of a task
 * as per input by user.
 */
public final class DeadLineTask extends Task {
    /** Stores the type of task information that identifies a DeadlineTask */
    private final String taskType = "[D]";

    /** Stores the do-by-date information */
    private final String dueDate;

    /** Stores the do-by-date information as a LocalDate */
    private LocalDate localDate;

    /**
     * Constructs a Deadline task and check if a date can be
     * stored as LocalDate. If so, save it, otherwise save
     * date as per user input.
     *
     * @param description the input string to describe the Deadline task
     * @param date the do-by-date
     */
    public DeadLineTask(String description, String date) {
        super(description);
        this.dueDate = date;
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
     * @return the date associated with task in as a LocalDate
     */
    @Override
    public LocalDate getLocalDate() {
        return this.localDate;
    }

    /**
     * Retrieves the description specific to the Deadline task
     * and use a LocalDate if available.
     *
     * @return the description specific to the Deadline task
     */
    @Override
    public String getDescription() {
        if (this.localDate == null) {
            return super.getDescription() + " " + "(by: " + this.dueDate + ")";
        } else {
            return super.getDescription() + " " + "(by: " + this.localDate.getDayOfMonth() + " "
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
            return "D" + "|" + this.getSimpleTaskDescription().strip() + "|" + this.dueDate + "|" + 0;
        } else {
            return "D" + "|" + this.getSimpleTaskDescription().strip() + "|" + this.dueDate + "|" + 1;
        }
    }
}

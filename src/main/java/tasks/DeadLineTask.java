package tasks;

import duke.DukeException;

import java.time.DateTimeException;
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
     * @throws DukeException thrown when date input format is not supported
     */
    public DeadLineTask(String description, String date) throws DukeException {
        super(description);
        LocalDate day = getDate(date);
        if (day == null) {
            throw new DukeException("Invalid date format! Please input 'DD/MM/YYYY'.");
        }
        this.dueDate = date;
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
        if (getStatus().equals("[ ]")) {
            return "D" + "|" + getSimpleTaskDescription().strip() + "|" + this.dueDate + "|" + 0;
        } else {
            return "D" + "|" + getSimpleTaskDescription().strip() + "|" + this.dueDate + "|" + 1;
        }
    }
}

package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;

/**
 * The DeadlineTask Class inherits Task Class
 * to support different specificities of a task
 * as per input by user.
 */
public final class DeadLineTask extends Task {

    /** Stores the type of task information that identifies a DeadlineTask */
    private final String taskType = "[D]";

    /** Stores the do-by-date information */
    private String dueDate;

    /** Stores the do-by-date information as a LocalDate */
    private LocalDate localDate;

    /** Stores the time information as a LocalTime */
    private LocalTime time;

    /** Stores the information if the task has a valid date */
    private boolean hasValidDate;

    /** Stores the information if the task has a valid time */
    private boolean hasValidTime;

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
        setDateAndTime(date);
    }

    private void setDateAndTime(String userInput) {
        String dateAndTime = "[0-9]{1,2}[\\\\/][0-9]{1,2}[\\\\/][0-9]{4}\\s[0-9]{4}";
        String dateOnly = "[0-9]{1,2}[\\\\/][0-9]{1,2}[\\\\/][0-9]{4}";
        Pattern p = Pattern.compile(dateAndTime);
        Matcher m = p.matcher(userInput);
        String dateStr;
        String matched;
        if (m.find()) {
            matched = m.group();
            dateStr = matched.substring(0, matched.length() - 4)
                    .replaceAll(" ", "");
            try {
                String[] date = dateStr.split("/");
                int day = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                setLocalDate(LocalDate.of(year, month, day));
                hasValidDate = true;
            } catch (DateTimeException e) {
                hasValidDate = false;
            }
            try {
                setTimeFromString(matched.substring(matched.length() - 4));
                hasValidTime = true;
            } catch (DukeException e) {
                hasValidTime = false;
            }
        } else {
            p = Pattern.compile(dateOnly);
            m = p.matcher(userInput);
            if (m.find()) {
                dateStr = m.group();
                try {
                    String[] date = dateStr.split("/");
                    int day = Integer.parseInt(date[0]);
                    int month = Integer.parseInt(date[1]);
                    int year = Integer.parseInt(date[2]);
                    setLocalDate(LocalDate.of(year, month, day));
                    hasValidDate = true;
                } catch (DateTimeException e) {
                    hasValidDate = false;
                }
            }
        }
    }

    /**
     * Retrieves the information if this task has a valid date.
     * @return if the task has a valid date
     */
    public boolean hasValidDate() {
        return hasValidDate;
    }

    /**
     * Retrieves the information if this task has a valid time.
     * @return if the task has a valid time
     */
    public boolean hasValidTime() {
        return hasValidTime;
    }

    private void setTimeFromString(String time) throws DukeException {
        try {
            this.time = LocalTime.of(Integer.parseInt(time.substring(0, 2)),
                    Integer.parseInt(time.substring(time.length() - 2)));
            if (localDate != null) {
                dueDate += " " + this.time.toString().replaceAll(":", "");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please use a number!");
        } catch (NullPointerException e) {
            throw new DukeException("Not a valid time!");
        } catch (DateTimeException e) {
            throw new DukeException("Invalid values!");
        }
    }

    /**
     * Sets the time as a valid time.
     * @param localTime the specific valid time to store
     */
    public void setLocalTime(LocalTime localTime) {
        if (localTime != null) {
            this.time = localTime;
            if (this.localDate != null) {
                dueDate += " " + localTime.toString().replaceAll(":", "");
            }
        }
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
        this.time = null;
        this.localDate = localDate;
        this.dueDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
        } else if (this.time == null) {
            return super.getDescription() + " " + "(by: " + this.localDate.getDayOfMonth() + " "
                    + Month.of(this.localDate.getMonthValue()) + " " + this.localDate.getYear() + ")";
        } else {
            return super.getDescription() + " " + "(by: " + this.localDate.getDayOfMonth() + " "
                    + Month.of(this.localDate.getMonthValue()) + " " + this.localDate.getYear() + " "
                    + time + ")";
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

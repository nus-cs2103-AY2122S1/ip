package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.parser.Parser;

/**
 * Represents the task user want to configure.
 *
 * @author QIN GUORUI
 */
public class Task {
    /**
     * The content of the task.
     */
    protected String description;

    /**
     * Whether the task is finished or not.
     */
    protected boolean isDone;

    /**
     * Sets up the task.
     *
     * @param description The content of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns the correct string form of date.
     *
     * @param date The date form in yy-mm-dd.
     * @return The specified string form of date.
     */
    public static String getDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the date representation from the user input.
     *
     * @param date The date made by user.
     * @return The date transformed in standard form.
     */
    public static String transferToDateFormat(String date) {
        int lens = date.length();
        if (lens > 10) {
            date = date.substring(0, lens - 5);
        }
        String[] parts = date.split("/");
        if (parts[0].length() < 2) {
            parts[0] = "0" + parts[0];
        }
        if (parts[1].length() < 2) {
            parts[1] = "0" + parts[1];
        }
        date = parts[2] + "-" + parts[1] + "-" + parts[0];
        return date;
    }

    /**
     * Returns whether the year,month and day are valid.
     *
     * @param parts The user input of date in parts.
     * @return A boolean.
     */
    public static boolean isDateFirstPart(String[] parts) {
        if (parts.length != 3) {
            return false;
        }
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];
        int dayValue = Integer.parseInt(day);
        int monthValue = Integer.parseInt(month);
        boolean validDay = dayValue <= 31 && dayValue >= 1;
        boolean validMonth = monthValue <= 12 && monthValue >= 1;
        return Parser.chekDigit(day) && Parser.chekDigit(year) && Parser.chekDigit(month) && validDay
                && validMonth;
    }

    /**
     * Returns whether the input is date or not.
     *
     * @param input The user input of date.
     * @return True or not.
     */
    public static boolean isDate(String input) {
        int lens = input.length();
        if (lens > 10) {
            if (!input.contains(" ")) {
                return false;
            }
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                return false;
            }
            String date = parts[0];
            String time = parts[1];
            if (date.length() > 10 || time.length() != 4 || !Parser.chekDigit(time)
                    || !date.contains("/")) {
                return false;
            }
            String[] subParts = date.split("/");
            return isDateFirstPart(subParts);
        } else {
            if (lens < 8 || !input.contains("/")) {
                return false;
            }
            String[] parts = input.split("/");
            return isDateFirstPart(parts);
        }
    }

    /**
     * Returns the correct time representation in string.
     *
     * @param time The user input time.
     * @return The specified time representation.
     */
    public static String getTime(String time) {
        int hr1 = time.charAt(0) - '0';
        int hr2 = time.charAt(1) - '0';
        int hrFull = hr1 * 10 + hr2;
        int hrFinal;
        String day = " am";
        if (hrFull >= 12) {
            hrFinal = hrFull - 12;
            day = " pm";
        } else {
            hrFinal = hrFull;
        }
        return ((Integer) hrFinal).toString() + '.' + time.substring(2, 4) + day;
    }

    /**
     * Returns the time string in correct form.
     *
     * @param preTime The time user input.
     * @return The specified form of time.
     */
    public static String dateAndTime(String preTime) {
        if (isDate(preTime)) {
            int lens = preTime.length();
            String actualTime = getDate(transferToDateFormat(preTime));
            if (lens > 10) {
                actualTime += " " + getTime(preTime.substring(lens - 4, lens));
            }
            return actualTime;
        } else {
            return preTime;
        }
    }

    public boolean compareTime(String time) {
        return true;
    }

    /**
     * Returns whether the task have this content.
     *
     * @param content The content.
     * @return The boolean.
     */
    public boolean contains(String content) {
        return this.description.contains(content);
    }
}

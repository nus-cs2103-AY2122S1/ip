package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import duke.exception.MismatchedFormException;
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

    protected String atOrBy;
    /**
     * Sets up the task.
     *
     * @param description The content of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String atOrBy) {
        this.description = description;
        this.isDone = false;
        this.atOrBy = atOrBy;
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
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
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
        //Get the value of day and month.
        int dayValue = Integer.parseInt(day);
        int monthValue = Integer.parseInt(month);
        //Check whether they are valid or not.
        boolean isValidDay = dayValue <= 31 && dayValue >= 1;
        boolean isValidMonth = monthValue <= 12 && monthValue >= 1;
        boolean isDigitForm = Parser.checkIsDigit(day) && Parser.checkIsDigit(year) && Parser.checkIsDigit(month);
        return isDigitForm && isValidMonth && isValidDay;
    }

    /**
     * Returns whether the input is date or not.
     *
     * @param input The user input of date.
     * @return True or not.
     */
    public static int isDateInputFormat(String input) {
        int lens = input.length();
        if (lens > 10) {
            return checkDateTime(input) ? 2 : 0;
        } else {
           return checkOnlyDate(input, lens) ? 1 : 0;
        }
    }

    public static boolean checkDateTime(String input) {
        if (!input.contains(" ")) {
            return false;
        }
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            return false;
        }
        String date = parts[0];
        String time = parts[1];
        boolean isLengthInValid = date.length() > 10 || time.length() != 4;
        boolean isContentInValid = !Parser.checkIsDigit(time) || !date.contains("/");
        if (isLengthInValid || isContentInValid) {
            return false;
        }
        String[] subParts = date.split("/");
        return isDateFirstPart(subParts);
    }

    public static boolean checkOnlyDate(String input, int lens) {
        if (lens < 8 || !input.contains("/")) {
            return false;
        }
        String[] parts = input.split("/");
        return isDateFirstPart(parts);
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
    public static String formatOutputDateAndTime(String preTime) throws MismatchedFormException {
        int lens = preTime.length();
        if (isDateInputFormat(preTime) == 1) {
            return getDate(transferToDateFormat(preTime));
        } else if (isDateInputFormat(preTime) == 2) {
            return getDate(transferToDateFormat(preTime)) + " "
                    + getTime(preTime.substring(lens - 4, lens));
        } else {
            throw new MismatchedFormException("date part", "dd/mm/yyyy");
        }
    }

    public boolean isTimeSame(String time) {
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

    public String isWithinMonthOrDay(String date) {
            String[] partsEventDate = atOrBy.split(" ");
            String monthEvent = partsEventDate[0];
            String yearEvent = partsEventDate[2];
            String dayEvent = partsEventDate[1];
            String[] partsDate = date.split(" ");
            String monthDate = partsDate[0];
            String yearDate = partsDate[2];
            String dayDate = partsDate[1];
            boolean isYearAfter = Integer.parseInt(yearDate) == Integer.parseInt(yearEvent);
            boolean isMonthSame = monthDate.equals(monthEvent);
            boolean isDaySame = dayDate.equals(dayEvent);
            boolean isDayAfter = Integer.parseInt(dayDate) <= Integer.parseInt(dayEvent);
            boolean isWithinDay = isMonthSame && isYearAfter && isDaySame;
            boolean isWithinMonth = isMonthSame && isYearAfter && isDayAfter;
            return isWithinDay ? "day" : isWithinMonth ? "month" : "not within time period";
    }
}

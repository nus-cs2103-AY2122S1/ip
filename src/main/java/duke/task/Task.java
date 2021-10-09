package duke.task;

import java.time.LocalDate;
import java.time.Period;
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

    /**
     * The time stored in event or deadline.
     */
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

    /**
     * Overloads task command when event and deadline happen.
     *
     * @param description The task content.
     * @param atOrBy      The time when the task event or deadline happen.
     */
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
        String splitString = "/";
        if (lens > 10) { //certain that date input includes the time part.
            date = date.substring(0, lens - 5); //extract out the actual date part.
        }
        //Find each part of date input.
        String[] dateParts = date.split(splitString);
        String dayPart = dateParts[0];
        String monthPart = dateParts[1];
        String yearPart = dateParts[2];
        String day = dayPart;
        String month = monthPart;
        //modify if date input parts not satisfy the required format.
        if (dayPart.length() < 2) {
            day = "0" + day;
        }
        if (monthPart.length() < 2) {
            month = "0" + month;
        }
        return yearPart + "-" + month + "-" + day;
    }

    /**
     * Returns whether the year,month and day are valid.
     *
     * @param parts The user input of date in parts.
     * @return Whether first part of user input is date or not.
     */
    public static boolean isFirstPartDate(String[] parts) {
        if (parts.length != 3) { //The required format includes 3 parts.
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
    public static String isDateInputFormat(String input) {
        int lens = input.length();
        if (lens > 10) { //len > 10 means input includes the time part.
            return checkDateTime(input) ? "date and time" : "wrong format";
        } else {
            return checkOnlyDate(input, lens) ? "only date" : "wrong format";
        }
    }

    /**
     * Checks the date format and time format of user input.
     *
     * @param input The date input done by user.
     * @return Whether the date time format valid.
     */
    public static boolean checkDateTime(String input) {
        if (!input.contains(" ")) { //checks whether contains the split string.
            return false;
        }
        String[] parts = input.split(" ");
        if (parts.length != 2) { //required pattern has parts with length 2.
            return false;
        }
        String date = parts[0];
        String time = parts[1];
        String splitString = "/";
        //checks whether the length content valid or not.
        boolean isLengthInValid = date.length() > 10 || time.length() != 4; //time format should have length 4.
        boolean isContentInValid = !Parser.checkIsDigit(time) || !date.contains(splitString);
        if (isLengthInValid || isContentInValid) {
            return false;
        }
        String[] subParts = date.split(splitString);
        return isFirstPartDate(subParts);
    }

    /**
     * Checks only the date part of the user input.
     *
     * @param input The date input.
     * @param lens  The lens of date input.
     * @return Whether the format is date.
     */
    public static boolean checkOnlyDate(String input, int lens) {
        int judgeLength = 8; // If less than 8, check only date part, otherwise check both date and time.
        String splitString = "/";
        if (lens < judgeLength || !input.contains(splitString)) {
            return false;
        }
        String[] parts = input.split(splitString);
        return isFirstPartDate(parts);
    }

    /**
     * Returns the correct time representation in string.
     *
     * @param time The user input time.
     * @return The specified time representation.
     */
    public static String getTime(String time) {
        int hrTens = time.charAt(0) - '0';
        int hrOnes = time.charAt(1) - '0';
        int hrFull = hrTens * 10 + hrOnes; //number at hrTens should be multiplied by 10.
        int hrFinal;
        String dayPart = " am";
        int noonTime = 12;
        if (hrFull >= noonTime) {
            hrFinal = hrFull - noonTime;
            dayPart = " pm";
        } else {
            hrFinal = hrFull;
        }
        int minStart = 2;
        int minEnd = 4;
        return ((Integer) hrFinal).toString() + '.' + time.substring(minStart, minEnd) + dayPart;
    }

    /**
     * Returns the time string in correct form.
     *
     * @param preTime The time user input.
     * @return The specified form of time.
     * @throws MismatchedFormException The exception happened when the date format is incorrect.
     */
    public static String formatOutputDateAndTime(String preTime) throws MismatchedFormException {
        int lens = preTime.length();
        if (isDateInputFormat(preTime).equals("only date")) {
            return getDate(transferToDateFormat(preTime));
        } else if (isDateInputFormat(preTime).equals("date and time")) {
            return getDate(transferToDateFormat(preTime)) + " "
                    + getTime(preTime.substring(lens - 4, lens)); //last four characters represent the time.
        } else {
            throw new MismatchedFormException("date part", "dd/mm/yyyy");
        }
    }

    /**
     * Returns whether the two times are equal.
     *
     * @param time The time's string representation.
     * @return Whether time same or not.
     */
    public boolean isTimeSame(String time) {
        return atOrBy.equals(time);
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

    /**
     * Checks the date is within a month or within a day to happen.
     *
     * @param date The date input.
     * @return The string representing the month, day or not recognised.
     */
    public String withinMonthOrWeek(String date) {
        String splitString = " ";
        String dateDifference = this.getDatesDifferences(date);
        String[] differenceParts = dateDifference.split(splitString);
        String yearDifference = differenceParts[0];
        String monthDifference = differenceParts[1];
        String dayDifference = differenceParts[2];
        String dayAfter = differenceParts[3];
        String monthAfter = differenceParts[4];
        //judge within the month or week.
        boolean isLessThanYear = yearDifference.equals("0");
        boolean isLessThanMonth = monthDifference.equals("0");
        boolean isMonthAfter = monthAfter.equals("yes");
        boolean isDayAfter = dayAfter.equals("yes");
        boolean isWithinMonth = isLessThanMonth && isLessThanYear && isMonthAfter && isDayAfter;
        boolean isWithinWeek = isWithinMonth && Integer.parseInt(dayDifference) < 7;
        //format the output.
        return isWithinWeek ? "week" : isWithinMonth ? "month" : "not within time period";
    }

    /**
     * Returns the int form of a month.
     *
     * @param month The string representation of month.
     * @return The int form of month.
     */
    public int toMonthIntForm(String month) {
        switch (month) {
        case "Jan":
            return 1;
        case "Feb":
            return 2;
        case "Mar":
            return 3;
        case "Apr":
            return 4;
        case "May":
            return 5;
        case "Jun":
            return 6;
        case "Jul":
            return 7;
        case "Aug":
            return 8;
        case "Sep":
            return 9;
        case "Oct":
            return 10;
        case "Nov":
            return 11;
        default:
            return 12;
        }
    }

    /**
     * Returns the date differences between instance date and given date.
     *
     * @param date The given date.
     * @return The date difference using year month and day.
     */
    public String getDatesDifferences(String date) {
        String splitString = " ";
        //get int form of the year month and day of an event.
        String[] partsEventDate = atOrBy.split(splitString);
        int yearEvent = Integer.parseInt(partsEventDate[2]);
        int monthEvent = toMonthIntForm(partsEventDate[0]);
        int dayEvent = Integer.parseInt(partsEventDate[1]);
        //get int form of the year month and day of a date.
        String[] partsDate = date.split(splitString);
        int yearDate = Integer.parseInt(partsDate[2]);
        int monthDate = toMonthIntForm(partsDate[0]);
        int dayDate = Integer.parseInt(partsDate[1]);
        //Initialise the dates using int form of year month and day provided before.
        LocalDate from = LocalDate.of(yearDate, monthDate, dayDate);
        LocalDate to = LocalDate.of(yearEvent, monthEvent, dayEvent);
        //Calculate the difference in dates.
        Period period = Period.between(from, to);
        //Ensure past events not counted.
        String monthAfter = "no";
        if (monthDate <= monthEvent || (monthDate == 12 && monthEvent == 1)) {
            monthAfter = "yes";
        }
        String dayAfter = "no";
        if (dayDate <= dayEvent || monthDate < monthEvent) {
            dayAfter = "yes";
        }
        return period.getYears() + splitString + period.getMonths() + splitString + period.getDays()
                + splitString + dayAfter + splitString + monthAfter;
    }
}

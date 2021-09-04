package duke.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import duke.data.exceptions.InvalidDateAndTimeException;
import duke.ui.Message;

//todo documentation

/**
 * Represents a DateAndTime object that reformats the date and time provided by the user to a specified format.
 */
public class DateAndTime {
    private String dateAndTimeFromCommand;
    private boolean isDatePresent = false;
    private boolean isTimePresent = false;

    public DateAndTime(String dateAndTimeFromCommand) {
        this.dateAndTimeFromCommand = dateAndTimeFromCommand;
    }

    /**
     * Returns a string representing the reformatted date and time provided by the user.
     *
     * @return reformatted date and time
     * @throws InvalidDateAndTimeException
     */
    public String getReformattedDateAndTime() throws InvalidDateAndTimeException {
        if (isDateAndTimePresent()) {
            if (isDatePresent && isTimePresent) {
                String dateAndTime = getDate() + "T" + getTime();
                LocalDateTime localDateTime = LocalDateTime.parse(dateAndTime);
                return localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
            } else if (isDatePresent) {
                String date = getDate();
                LocalDate localDate = LocalDate.parse(date);
                return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } else {
                String time = getTime();
                LocalTime localTime = LocalTime.parse(time);
                return localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            }
        } else {
            throw new InvalidDateAndTimeException(Message.MESSAGE_INVALID_DATE_AND_TIME);
        }
    }

    private String getDate() throws InvalidDateAndTimeException {
        int firstSlashIndex = dateAndTimeFromCommand.indexOf("/");
        int secondSlashIndex = dateAndTimeFromCommand.lastIndexOf("/");
        if (firstSlashIndex < 0 || secondSlashIndex < 0) {
            throw new InvalidDateAndTimeException(Message.MESSAGE_INVALID_DATE_AND_TIME);
        }

        int startingIndex = firstSlashIndex - 2;
        int endingIndex = secondSlashIndex + 4;

        boolean isIndexesValid = startingIndex >= 0 || endingIndex <= dateAndTimeFromCommand.length() - 1;
        if (!isIndexesValid) {
            throw new InvalidDateAndTimeException(Message.MESSAGE_INVALID_DATE_AND_TIME);
        }

        boolean isDateCharacterValid = isDateCharacterValid(startingIndex, endingIndex);
        if (isDateCharacterValid) {
            return isDateValid(startingIndex, endingIndex).toString();
        }
        throw new InvalidDateAndTimeException(Message.MESSAGE_INVALID_DATE_AND_TIME);
    }

    private boolean isDateCharacterValid(int start, int end) throws InvalidDateAndTimeException {
        for (int i = start; i <= end; i++) {
            char currentChar = dateAndTimeFromCommand.charAt(i);
            if ((i == start + 2) || (i == end - 4)) { //slash positions
                if (currentChar != '/') {
                    throw new InvalidDateAndTimeException(Message.MESSAGE_INVALID_DATE);
                }
            } else {
                if (!Character.isDigit(currentChar)) {
                    throw new InvalidDateAndTimeException(Message.MESSAGE_INVALID_DATE);
                }
            }
        }
        return true;
    }

    private LocalDate isDateValid(int start, int end) throws InvalidDateAndTimeException {
        String dateInput = dateAndTimeFromCommand.substring(start, end + 1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        LocalDate date;
        try {
            date = LocalDate.parse(dateInput, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateAndTimeException(e.getMessage()); //todo format error message?
        }
        return date;
    }

    private boolean isDateAndTimePresent() {
        int colonIndex = dateAndTimeFromCommand.indexOf(":");
        int firstSlashIndex = dateAndTimeFromCommand.indexOf("/");
        int secondSlashIndex = dateAndTimeFromCommand.lastIndexOf("/");

        boolean isSlashPresent = firstSlashIndex >= 0 && secondSlashIndex >= 0;
        boolean isDatePresent = isSlashPresent && firstSlashIndex != secondSlashIndex;
        boolean isTimePresent = colonIndex > 0;

        if (!isDatePresent && !isTimePresent) {
            return false;
        } else {
            if (isDatePresent) {
                this.isDatePresent = true;
            }

            if (isTimePresent) {
                this.isTimePresent = true;
            }
            return true;
        }
    }

    private String getTime() throws InvalidDateAndTimeException { //format of hh:mm
        int colonIndex = dateAndTimeFromCommand.indexOf(":");
        int startingIndex = colonIndex - 2;
        int endingIndex = colonIndex + 2;

        if (startingIndex < 0 || endingIndex > dateAndTimeFromCommand.length() - 1) {
            throw new InvalidDateAndTimeException(Message.MESSAGE_INVALID_TIME);
        }

        boolean isTimeFormatValid = isTimeFormatValid(startingIndex, endingIndex);

        if (isTimeFormatValid) {
            return isTimeValid(startingIndex, endingIndex).toString();
        } else {
            throw new InvalidDateAndTimeException(Message.MESSAGE_INVALID_TIME);
        }
    }

    private boolean isTimeFormatValid(int start, int end) throws InvalidDateAndTimeException {
        boolean isHourTensValid = Character.isDigit(dateAndTimeFromCommand.charAt(start));
        boolean isHourOnesValid = Character.isDigit(dateAndTimeFromCommand.charAt(start + 1));
        boolean isMinTensValid = Character.isDigit(dateAndTimeFromCommand.charAt(end - 1));
        boolean isMinOnesValid = Character.isDigit(dateAndTimeFromCommand.charAt(end));
        boolean isTimeLengthValid = isTimeLengthValid(start, end);
        return isHourTensValid && isHourOnesValid && isMinTensValid && isMinOnesValid && isTimeLengthValid;
    }

    private boolean isTimeLengthValid(int start, int end) {
        if (start == 0) {
            return end == dateAndTimeFromCommand.length() - 1;
        } else {
            return Character.isWhitespace(start - 1) && (end == dateAndTimeFromCommand.length() - 1);
        }
    }

    private LocalTime isTimeValid(int start, int end) throws InvalidDateAndTimeException {
        String timeInput = dateAndTimeFromCommand.substring(start, end + 1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);
        LocalTime time;
        try {
            time = LocalTime.parse(timeInput, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateAndTimeException(e.getMessage()); //todo format error message?
        }
        return time;
    }
}

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//todo exception for range of values for mth, day, year, hr, min
//should have /by or /at alr when initialising this class
public class DateAndTime {
    private String dateAndTimeFromCommand;
    private boolean isDatePresent = false;
    private boolean isTimePresent = false;

    public DateAndTime(String dateAndTimeFromCommand) {
        this.dateAndTimeFromCommand = dateAndTimeFromCommand;
    }

    public String getReformattedDateAndTime() throws InvalidDateAndTimeException {
        if (isDateAndTimeValid()) {
            if (isDatePresent && isTimePresent) {
                String parseFormat = getDate() + "T" + getTime();
                LocalDateTime localDateTime = LocalDateTime.parse(parseFormat);
                return localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
            } else if (isDatePresent) {
                String parseFormat = getDate();
                LocalDate localDate = LocalDate.parse(parseFormat);
                return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } else {
                String parseFormat = getTime();
                LocalTime localTime = LocalTime.parse(parseFormat);
                return localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            }
        } else {
            throw new InvalidDateAndTimeException("invalid date and time");
        }
    }

    public boolean isDateAndTimeValid() {
        int colonIndex = dateAndTimeFromCommand.indexOf(":");
        int lastSlashIndex = dateAndTimeFromCommand.lastIndexOf("/");
        int firstSlashIndex = dateAndTimeFromCommand.indexOf("/");

        if ((colonIndex < 0) && (firstSlashIndex == lastSlashIndex)) {
            return false;
        } else {
            if (colonIndex > 0) {
                this.isTimePresent = true;
            }

            if (firstSlashIndex != lastSlashIndex) {
                this.isDatePresent = true;  //todo when deadline provided has no dashes eg /at 11 11:00
            }

            return true;
        }
    }

    public String getTime() throws InvalidDateAndTimeException { //format of hh:mm
        int colonIndex = dateAndTimeFromCommand.indexOf(":");

        if (((dateAndTimeFromCommand.length() - (colonIndex + 2)) != 1) || (colonIndex - 3) < 0) {
            throw new InvalidDateAndTimeException("invalid time");
        }

        boolean isHourTensValid = Character.isDigit(dateAndTimeFromCommand.charAt(colonIndex - 2));
        boolean isHourOnesValid = Character.isDigit(dateAndTimeFromCommand.charAt(colonIndex - 1));
        boolean isMinTensValid = Character.isDigit(dateAndTimeFromCommand.charAt(colonIndex + 1));
        boolean isMinOnesValid = Character.isDigit(dateAndTimeFromCommand.charAt(colonIndex + 2));
        boolean isTimeLengthValid = Character.isWhitespace(dateAndTimeFromCommand.charAt(colonIndex - 3)) &&
                dateAndTimeFromCommand.length() == (colonIndex + 3);

        if (!isTimeLengthValid| !isHourTensValid | !isHourOnesValid | !isMinTensValid | !isMinOnesValid) {
            throw new InvalidDateAndTimeException("invalid time");
        } else {
            return dateAndTimeFromCommand.substring(colonIndex - 2, colonIndex + 3) + ":00";
        }
    }

    //todo invalid date error has random dot printed?
    public String getDate() throws InvalidDateAndTimeException { //format of dd/mm/yyyy
        int lastSlashIndex = dateAndTimeFromCommand.lastIndexOf("/");

        if (((lastSlashIndex + 5) > dateAndTimeFromCommand.length()) || (lastSlashIndex - 6) < 0) {
            throw new InvalidDateAndTimeException("invalid date");
        }

        boolean isDateLengthValid = Character.isWhitespace(dateAndTimeFromCommand.charAt(lastSlashIndex - 6)) &&
                (dateAndTimeFromCommand.length() == (lastSlashIndex + 5) ||
                        Character.isWhitespace(dateAndTimeFromCommand.charAt(lastSlashIndex + 5)));
        boolean isDayValid = checkDate(lastSlashIndex - 5, 2);
        boolean isMonthValid = checkDate(lastSlashIndex - 2, 2);
        boolean isYearValid = checkDate(lastSlashIndex + 1, 4);

        if (!isDateLengthValid | !isDayValid | !isMonthValid | !isYearValid) {
            throw new InvalidDateAndTimeException("invalid date");
        } else {
            return dateAndTimeFromCommand.substring(lastSlashIndex + 1, lastSlashIndex + 5) + "-"
                    + dateAndTimeFromCommand.substring(lastSlashIndex - 2, lastSlashIndex) + "-"
                    + dateAndTimeFromCommand.substring(lastSlashIndex - 5, lastSlashIndex - 3);
        }

    }

    public boolean checkDate(int index, int formatLength) {
        boolean result = true;
        for (int i = 0; i < formatLength; i++) {
            result = result && Character.isDigit(dateAndTimeFromCommand.charAt(index + i));
        }
        return result;
    }
}

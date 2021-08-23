import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class DateTime {

    private static String errorMessage = "Wrong format Sir/Mdm. Dates and times must be given as only a date: DATE\n"
            + "or as date and time: DATE TIME\n"
            + "Accepted formats for DATE: YYYY-MM-DD, DD/MM/YYYY\n"
            + "Accepted formats for TIME (24H format): TT:TT, TTTT\n"
            + "Examples for DATE TIME: 13/2/2019 1800, 13/2/2019 18:00, 2019-02-13 1800,\n"
            + "2019-02-13 18:00\n"
            + "Examples for DATE: 13/2/2019, 2019-02-13";

    public static LocalDateTime parseDateAndTime(String dateInput, String timeInput) throws DukeException {


        LocalDate date = parseDate(dateInput);
        LocalTime time = parseTime(timeInput);
        return LocalDateTime.of(date, time);

    }


    public static LocalDate parseDate(String input) throws DukeException {
        LocalDate date;
        try {
            date = LocalDate.parse(input);
        } catch (DateTimeException e) {
            String[] dayMonthAndTime = input.split("/");
            if (dayMonthAndTime.length != 3) {
                throw new DukeException(errorMessage);
            }
            try {
                int day = Integer.parseInt(dayMonthAndTime[0]);
                int month = Integer.parseInt(dayMonthAndTime[1]);
                int year = Integer.parseInt(dayMonthAndTime[2]);

                date = LocalDate.of(year, month, day);
            } catch (DateTimeException | NumberFormatException f) {
                throw new DukeException(errorMessage);
            }
        }
        return date;
    }


    public static LocalTime parseTime(String input) throws DukeException {
        LocalTime time;
        try {
            time = LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            if (input.length() != 4) {
                throw new DukeException(errorMessage);
            }
            try {
                int hour = Integer.parseInt(input.substring(0, 2));
                int minute = Integer.parseInt(input.substring(2));
                time = LocalTime.of(hour, minute);
            } catch (NumberFormatException | DateTimeException f) {
                throw new DukeException(errorMessage);
            }
        }

        return time;
    }


    public static String dateTimeToString(LocalDateTime dateTime, boolean isDateOnly) {

        int len = dateTime.toString().length();

        if (isDateOnly) {
            return String.format("%s %s %s", dateTime.getDayOfMonth(), dateTime.getMonth().toString().substring(0, 1)
                    + dateTime.getMonth().toString().substring(1).toLowerCase(), dateTime.getYear());
        }
        return String.format("%s %s %s %s", dateTime.getDayOfMonth(), dateTime.getMonth().toString().substring(0, 1)
                        + dateTime.getMonth().toString().substring(1).toLowerCase(), dateTime.getYear(),
                dateTime.toString().substring(len - 5));
    }

}
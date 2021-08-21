package tiger.utils;

import tiger.exceptions.inputs.TigerDateParsingException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateStringConverter {

    public CustomDate getDateFromString(String input) throws TigerDateParsingException {
        String editedInput = new RemoveSpaces().removeBackAndFrontSpaces(input);
        String[] array = editedInput.split(" ");
        try {
            assert (array.length <= 2);
            if (array.length == 1) {
                // means that the user has only inputted the date or the time, but not both
                assert(array[0].contains(":") || array[0].contains("-"));
                // TODO: add support for am, pm
                // TODO; add support for other date formats: ie 2021/12/21
                if (array[0].contains("-")) {
                    // means the first entry is the date
                    String dateString = new RemoveSpaces().removeBackAndFrontSpaces(array[0]);
                    LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(dateString));
                    LocalTime time = LocalTime.from(DateTimeFormatter.ofPattern("HH:mm").parse("00:00"));
                    return new CustomDate(date, time, false);
                } else {
                    // assume user wants date to be today,
                    LocalDate date = LocalDate.now();
                    String timeString = new RemoveSpaces().removeBackAndFrontSpaces(array[0]);
                    LocalTime time = LocalTime.from(DateTimeFormatter.ofPattern("HH:mm").parse(timeString));
                    return new CustomDate(date, time, true);
                }
            } else {
                // means the user input is in the format dd/MM/yyyy HH:mm
                String dateString = new RemoveSpaces().removeBackAndFrontSpaces(array[0]);
                String timeString = new RemoveSpaces().removeBackAndFrontSpaces(array[1]);
                LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(dateString));
                LocalTime time = LocalTime.from(DateTimeFormatter.ofPattern("HH:mm").parse(timeString));
                return new CustomDate(date, time, true);
            }
        } catch (DateTimeParseException e) {
            throw new TigerDateParsingException(e.toString());
        } catch (AssertionError e) {
            throw new TigerDateParsingException(e.toString());
        }
    }

    public static void main(String[] args) {
        CustomDate customDate = new DateStringConverter().getDateFromString("23:59");
        System.out.println(customDate.toString());
    }
}

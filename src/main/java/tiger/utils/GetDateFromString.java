package tiger.utils;

import tiger.exceptions.inputs.TigerDateParsingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GetDateFromString {

    public GetDateFromString() {

    }

    public CustomDate getDateFromString(String input) throws TigerDateParsingException {
        String[] array = input.split(" ");
        try {
            assert (array.length <= 2);
            if (array.length == 1) {
                // means that the user has only inputted the date or the time, but not both
                assert(array[0].contains(":") || array[0].contains("/"));
                // TODO: add support for am, pm
                // TODO: remove back and front spaces on date
                if (array[0].contains("/")) {
                    // means the first entry is the date
                    LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(array[0]));
                    LocalTime time = LocalTime.from(DateTimeFormatter.ofPattern("HH:mm").parse("00:00"));
                    return new CustomDate(date, time, false);
                } else {
                    // assume user wants date to be today,
                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.from(DateTimeFormatter.ofPattern("HH:mm").parse(array[0]));
                    return new CustomDate(date, time, true);
                }
            } else {
                // means the user input is in the format dd/MM/yyyy HH:mm
                LocalDate date = LocalDate.from(DateTimeFormatter.ofPattern("dd/MM/yyyy").parse(array[0]));
                LocalTime time = LocalTime.from(DateTimeFormatter.ofPattern("HH:mm").parse(array[1]));
                return new CustomDate(date, time, true);
            }
        } catch (DateTimeParseException e) {
            throw new TigerDateParsingException(e.toString());
        }
    }

    public static void main(String[] args) {
        CustomDate customDate = new GetDateFromString().getDateFromString(" 08:00");
        System.out.println(customDate.toString());
    }
}

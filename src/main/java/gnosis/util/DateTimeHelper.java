package gnosis.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is a helper class to convert datetime
 * to/from a string and date.
 *
 * @author Pawandeep Singh
 *
 * */
public class DateTimeHelper {
    /**
     * Converts a string to a DateTime.
     *
     * @param dateString to convert to a date.
     * @return LocalDateTime formatted datetime.
     * @throws GnosisException If String date does not match date format.
     */
    public static LocalDateTime stringToDate(String dateString) throws GnosisException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

        LocalDateTime ldt;
        try {
            ldt = LocalDateTime.parse(dateString.stripLeading(), formatter);
        } catch (DateTimeParseException e) {
            throw new GnosisException(GnosisConstants.DATETIME_FORMAT_EXCEPT_MESSAGE);
        }

        return ldt;
    }

    /**
     * Converts a datetime to a string format.
     * String format follows : MMM dd yyyy, hh:mma
     *
     * @param dateTimeToConvert datetime to convert.
     * @return strDateTime formatted datetime String.
     */
    public static String dateToString(LocalDateTime dateTimeToConvert) {
        return dateTimeToConvert.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma"));
    }
}

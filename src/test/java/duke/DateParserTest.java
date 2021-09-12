package duke;

import exceptions.DukeInvalidDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateParserTest {

    private static final LocalDate testDate = LocalDate.of(2021, 8, 5);

    @Test
    public void nonExistentDate() {
        assertThrows(DukeInvalidDateException.class, () ->
            DateParser.parseDate("30/15/2021"));
    }

    @Test
    public void wronglyFormattedDate() {
        assertThrows(DukeInvalidDateException.class, () ->
            DateParser.parseDate("30-02-2021"));
    }

    @Test
    public void dateWithSingleDigitMonthAndDay() throws DukeInvalidDateException {
        assertEquals(testDate, DateParser.parseDate("5/8/21"));
    }

    @Test
    public void wronglyFormattedTime() {
        assertThrows(DukeInvalidDateException.class, () ->
            DateParser.parseDateTime("05/08/2021 705"));
    }

}

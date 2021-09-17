package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeDateTest {

    @Test
    public void formatDateTimeTest() {
        String literalDateTime = "2/12/2019 1800";
        String parsedDate = DukeDate.parseDateToString(DukeDate.formatDate(literalDateTime));
        String expected = "Mon, Dec 02 2019, 18:00";
        assertEquals(expected, parsedDate);
    }

    @Test
    public void formatDateTest() {
        String literalDateTime = "2/12/2019";
        String parsedDate = DukeDate.parseDateToString(DukeDate.formatDate(literalDateTime));
        String expected = "Mon, Dec 02 2019, 00:00";
        assertEquals(expected, parsedDate);
    }

}

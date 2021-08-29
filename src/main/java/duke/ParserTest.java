package duke;


import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class ParserTest {

    @Test
    public static void parseDateTest1() {
        String datetimeString = "2009-01-01 10:00";
        assertEquals(LocalDateTime.of(2009, 01, 01, 10, 00), Parser.parseDate(datetimeString));
    }

    @Test
    public static void parseDatTest2() {
        String datetimeString = "2009-01-01 23:00";
        assertEquals(LocalDateTime.of(2009, 01, 01, 23, 00), Parser.parseDate(datetimeString));
    }
}

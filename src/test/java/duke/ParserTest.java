package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class ParserTest {

    @Test
    public void parseDateTest1() {
        String datetimeString = "2009-01-01 10:00";
        assertEquals(LocalDateTime.of(2009, 01, 01, 10, 00), Parser.parseDate(datetimeString));
    }

    @Test
    public void parseDatTest2() {
        String datetimeString = "2009-01-01 23:00";
        assertEquals(LocalDateTime.of(2009, 01, 01, 23, 00), Parser.parseDate(datetimeString));
    }
}

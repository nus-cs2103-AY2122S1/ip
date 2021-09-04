package duke.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class ParserTest {

    @Test
    void testParseDate() {
        try {
            assertEquals(LocalDate.parse("2021-08-23"), Parser.parseDate("23/08/2021"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testParseTime() {
        try {
            assertEquals(LocalTime.parse("20:00"), Parser.parseTime("2000"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testDeadlineDateTimeSplit() {
        try {
            String[] info = {"23/08/2021", "1800"};
            assertArrayEquals(info, Parser.splitDeadlineDateTime("23/08/2021 1800"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testEventDateTimeSplit() {
        try {
            String[] info = {"23/08/2021", "1800", "2100"};
            assertArrayEquals(info, Parser.splitEventDateTime("23/08/2021 1800-2100"));
        } catch (DukeException e) {
            fail();
        }
    }
}

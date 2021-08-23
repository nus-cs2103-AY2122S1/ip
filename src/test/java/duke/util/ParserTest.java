package duke.util;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

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
            assertArrayEquals(info, Parser.deadlineDateTimeSplit("23/08/2021 1800"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testEventDateTimeSplit() {
        try {
            String[] info = {"23/08/2021", "1800", "2100"};
            assertArrayEquals(info, Parser.eventDateTimeSplit("23/08/2021 1800-2100"));
        } catch (DukeException e) {
            fail();
        }
    }
}
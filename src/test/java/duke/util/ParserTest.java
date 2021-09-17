package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void extractIndexTest() throws DukeException {
        assertEquals(2, Parser.extractIndex("done 2"));
        assertEquals(3, Parser.extractIndex("delete 3"));
    }

    @Test
    public void parseDateTimeTest() throws ParseException {
        assertEquals(new SimpleDateFormat("yyyy-MM-dd hhmm").parse("2021-08-09 1800"),
                Parser.parseDateTime("2021-08-09 1800"));
    }

    @Test
    public void parseDateTest() throws ParseException {
        assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-09"),
                Parser.parseDateTime("2021-08-09"));
    }
}

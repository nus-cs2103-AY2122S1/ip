package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class ParserTest {
    @Test
    public void testGetAction() {
        assertEquals(Action.LIST, Parser.getAction("list"));
        assertEquals(Action.DEADLINE, Parser.getAction("deadline"));
        assertEquals(Action.UNKNOWN, Parser.getAction("blah"));
    }

    @Test
    public void testParseIsDone() {
        assertEquals(false, Parser.parseIsDone("0"));
        assertEquals(true, Parser.parseIsDone("1"));
    }

    @Test
    public void testParseDateTime() {
        assertEquals(LocalDateTime.parse("2020-01-20T00:00"), Parser.parseDateTime("20/01/2020 00:00"));
        assertEquals(LocalDateTime.parse("2021-12-03T23:59"), Parser.parseDateTime("03/12/2021 23:59"));
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parseDateTime("20/1/2020 00:00");
        });
        assertEquals("OOPS!!! time should be in the format: DD/MM/YYYY HH:MM", exception.getMessage());
    }

    @Test
    public void testParseDocument() {
        assertEquals(new Deadline("project", false, LocalDateTime.parse("2021-08-25T23:59")),
                Parser.parseDocument("D,0,project,25/08/2021 23:59"));
        assertEquals(new Todo("read books", true), Parser.parseDocument("T,1,read books"));
        assertEquals(new Event("meeting", true, LocalDateTime.parse("2021-12-03T08:00")),
                Parser.parseDocument("E,1,meeting,03/12/2021 08:00"));
        DukeException exception = assertThrows(DukeException.class, () -> {
            Parser.parseDocument("W,0,read books");
        });
        assertEquals("OOPS!!! Error in saved tasks document: W,0,read books", exception.getMessage());
    }
}

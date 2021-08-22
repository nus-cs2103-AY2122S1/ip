package duke.util;

import duke.exception.DukeException;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private final Parser parser = new Parser(new ToDoList(new ArrayList<Task>(), null), null);

    @Test
    public void extractIndexTest() throws DukeException {
        assertEquals(2, parser.extractIndex("done 2"));
        assertEquals(3, parser.extractIndex("delete 3"));
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

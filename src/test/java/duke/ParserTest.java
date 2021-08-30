package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
//    @Test
//    public void parseCommandTest() throws DukeException {
//        Parser parser = new Parser();
//        assertEquals("bye", parser.parseCommand("bYE"));
//    }
//
//    @Test
//    public void parseTodo() throws DukeException {
//        Parser parser = new Parser();
//        String[] strparse = new String[] {"todo", "kill", "me"};
//        assertEquals("kill me", parser.parseTodo(strparse).getTaskStr());
//    }

//    @Test
//    public void parseEvent() throws DukeException {
//        Parser parser = new Parser();
//        String[] strparse = new String[] {"event", "date", "/at", "2020-01-01", "01:00"};
//        assertEquals("2020-01-01 | 01:00", parser.parseEvent(strparse).getDateTimeStorage());
//    }

    @Test
    public void parseTime() throws DukeException {
        Parser parser = new Parser();
        LocalTime time = LocalTime.parse("13:00");
        assertEquals("1.00pm", parser.simplifyTime(time));
    }

}

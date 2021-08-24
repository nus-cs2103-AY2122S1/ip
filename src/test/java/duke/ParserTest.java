package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testIsValidTodo() {
        Parser parser = new Parser();
        String input = "todo read book";
        assertEquals(true, parser.isValidTodo(input));
    }

    @Test
    public void testGetDeadlineDescription() {
        Parser parser = new Parser();
        String input = "deadline return book /by Aug 20 2020 18:00";
        assertEquals("return book", parser.getDeadlineDescription(input));
    }

    @Test
    public void testGetEventTime() {
        Parser parser = new Parser();
        String input = "event project meeting /at Aug 20 2020 18:00";
        assertEquals("Aug 20 2020 18:00", parser.getEventTime(input));
    }

    @Test
    public void testparseLocalDateTime() {
        Parser parser = new Parser();
        String input = "2020-10-25 18:00";
        assertEquals("2020-10-25T18:00", parser.parseLocalDateTime(input).toString());
    }

}

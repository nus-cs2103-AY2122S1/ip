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

}

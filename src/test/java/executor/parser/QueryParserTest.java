package executor.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QueryParserTest {
    private final QueryParser parser = new QueryParser();

    @Test
    public void testTodo() {
        assertArrayEquals(parser.parse("todo eat breakfast"), new String[]{"todo", "eat breakfast"});
    }

    @Test
    public void testDeadline() {
        assertArrayEquals(parser.parse("deadline eat breakfast /by 2021-10-01"), new String[]{"deadline", "eat breakfast", "2021-10-01"});
    }

    @Test
    public void testEvent() {
        assertArrayEquals(parser.parse("event eat breakfast /at 2021-10-01"), new String[]{"event", "eat breakfast", "2021-10-01"});
    }
}

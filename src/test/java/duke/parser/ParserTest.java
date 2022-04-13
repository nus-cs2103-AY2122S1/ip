package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void checkForKeyword_noCommand_nullReturned() {
        Parser parser = new Parser();
        String result = parser.checkForKeyword("");
        assertEquals(null, result);
    }

    @Test
    public void checkForKeyword_invalidCommand_nullReturned() {
        Parser parser = new Parser();
        String result = parser.checkForKeyword("hello");
        assertEquals(null, result);

        result = parser.checkForKeyword("goodbye");
        assertEquals(null, result);

        result = parser.checkForKeyword(" deadline");
        assertEquals(null, result);

        result = parser.checkForKeyword("byebye");
        assertEquals(null, result);

        result = parser.checkForKeyword("to");
        assertEquals(null, result);

        result = parser.checkForKeyword("deleted");
        assertEquals(null, result);

        result = parser.checkForKeyword("remove");
        assertEquals(null, result);

        result = parser.checkForKeyword("logout");
        assertEquals(null, result);
    }

    @Test
    public void checkForKeyword_byeCommand_stringReturned() {
        Parser parser = new Parser();
        String result = parser.checkForKeyword("bye");
        assertEquals("bye", result);
    }

    @Test
    public void checkForKeyword_deadlineCommand_stringReturned() {
        Parser parser = new Parser();
        String result = parser.checkForKeyword("deadline");
        assertEquals("deadline", result);

        result = parser.checkForKeyword("deadline /by 2359");
        assertEquals("deadline", result);

        result = parser.checkForKeyword("deadline help prof /at 2012-03-14 23:59");
        assertEquals("deadline", result);

        result = parser.checkForKeyword("deadline finish lab /by 2014-03-12 01:11");
        assertEquals("deadline", result);
    }

    @Test
    public void checkForKeyword_eventCommand_stringReturned() {
        Parser parser = new Parser();
        String result = parser.checkForKeyword("event");
        assertEquals("event", result);

        result = parser.checkForKeyword("event /at 2359-0000");
        assertEquals("event", result);

        result = parser.checkForKeyword("event finish lab /by 2014-03-12 01:11");
        assertEquals("event", result);

        result = parser.checkForKeyword("event zoom prof /at 2012-03-14 23:59 00:00");
        assertEquals("event", result);
    }

    @Test
    public void checkForKeyword_todoCommand_stringReturned() {
        Parser parser = new Parser();
        String result = parser.checkForKeyword("todo");
        assertEquals("todo", result);

        result = parser.checkForKeyword("todo /at 2359-0000");
        assertEquals("todo", result);

        result = parser.checkForKeyword("todo finish lab /by 2014-03-12 01:11");
        assertEquals("todo", result);

        result = parser.checkForKeyword("todo zoom prof and take minutes");
        assertEquals("todo", result);
    }

    @Test
    public void checkForKeyword_deleteCommand_stringReturned() {
        Parser parser = new Parser();

        String result;

        result = parser.checkForKeyword("delete -1");
        assertEquals("delete", result);

        result = parser.checkForKeyword("delete 2");
        assertEquals("delete", result);
    }

    @Test
    public void checkForKeyword_doneCommand_stringReturned() {
        Parser parser = new Parser();

        String result;

        result = parser.checkForKeyword("done -1");
        assertEquals("done", result);

        result = parser.checkForKeyword("done 2");
        assertEquals("done", result);
    }

    @Test
    public void checkForKeyword_helpCommand_stringReturned() {
        Parser parser = new Parser();

        String result = parser.checkForKeyword("help");
        assertEquals("help", result);
    }

    @Test
    public void checkForKeyword_listCommand_stringReturned() {
        Parser parser = new Parser();

        String result = parser.checkForKeyword("list");
        assertEquals("list", result);
    }
}

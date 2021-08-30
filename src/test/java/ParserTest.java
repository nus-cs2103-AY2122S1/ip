import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import parser.Parser;


public class ParserTest {
    @Test
    public void parserCanParseAddEventWithManyWords() {
        String input = "event go for dance /at 01/02/2111 1200";
        HashMap<String, String> parsed = Parser.parseCommand(input);

        assertEquals("event", parsed.get("command"));
        assertEquals("go for dance", parsed.get("info"));
        assertEquals("01/02/2111 1200", parsed.get("at"));
    }

    @Test
    public void parserCanParseAddEventWithOneWord() {
        String input = "event dance /at 01/02/2111 1200";
        HashMap<String, String> parsed = Parser.parseCommand(input);

        assertEquals("event", parsed.get("command"));
        assertEquals("dance", parsed.get("info"));
        assertEquals("01/02/2111 1200", parsed.get("at"));
    }

    @Test
    public void parserCanParseAddDeadlineWithManyWords() {
        String input = "deadline submit 2103 /by 01/02/2111 1200";
        HashMap<String, String> parsed = Parser.parseCommand(input);

        assertEquals("deadline", parsed.get("command"));
        assertEquals("submit 2103", parsed.get("info"));
        assertEquals("01/02/2111 1200", parsed.get("by"));
    }

    @Test
    public void parserCanParseAddDeadlineWithOneWord() {
        String input = "deadline sleep /by 01/02/2111 1200";
        HashMap<String, String> parsed = Parser.parseCommand(input);

        assertEquals("deadline", parsed.get("command"));
        assertEquals("sleep", parsed.get("info"));
        assertEquals("01/02/2111 1200", parsed.get("by"));
    }

    @Test
    public void parserCanParseAddTodoWithManyWords() {
        String input = "todo submit 2103";
        HashMap<String, String> parsed = Parser.parseCommand(input);

        assertEquals("todo", parsed.get("command"));
        assertEquals("submit 2103", parsed.get("info"));
    }

    @Test
    public void parserCanParseAddTodoWithOneWord() {
        String input = "todo sleep";
        HashMap<String, String> parsed = Parser.parseCommand(input);

        assertEquals("todo", parsed.get("command"));
        assertEquals("sleep", parsed.get("info"));
    }

    @Test
    public void parserCanParseList() {
        String input = "list";
        HashMap<String, String> parsed = Parser.parseCommand(input);

        assertEquals("list", parsed.get("command"));
    }

    @Test
    public void parserCanParseDone() {
        String input = "done 1";
        HashMap<String, String> parsed = Parser.parseCommand(input);

        assertEquals("done", parsed.get("command"));
        assertEquals("1", parsed.get("info"));
    }

    @Test
    public void parserCanParseDelete() {
        String input = "delete 1";
        HashMap<String, String> parsed = Parser.parseCommand(input);

        assertEquals("delete", parsed.get("command"));
        assertEquals("1", parsed.get("info"));
    }


}

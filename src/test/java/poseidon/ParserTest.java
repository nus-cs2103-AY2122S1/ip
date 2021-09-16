package poseidon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import poseidon.parser.Parser;

public class ParserTest {

    @Test
    public void parse_todoCommand_stringArray() {
        String todoCommand = "todo finish all week 3 iP tasks";
        String[] todoCommandArray = new String[]{"add", "todo", "finish all week 3 iP tasks"};
        assertEquals(Arrays.toString(todoCommandArray), Arrays.toString(Parser.parse(todoCommand)));
    }

    @Test
    public void parse_deadlineCommand_stringArray() {
        String deadlineCommand = "deadline finish all week 3 iP tasks /by 2021 08 30 2359";
        String[] deadlineCommandArray = new String[]{"add", "deadline",
            "finish all week 3 iP tasks", "2021 08 30 2359"};
        assertEquals(Arrays.toString(deadlineCommandArray), Arrays.toString(Parser.parse(deadlineCommand)));
    }

    @Test
    public void parse_eventCommand_stringArray() {
        String eventCommand = "event morning lecture /from 2021 09 01 0900 to 2021 09 01 1000";
        String[] eventCommandArray = new String[]{"add", "event",
            "morning lecture", "2021 09 01 0900", "2021 09 01 1000"};
        assertEquals(Arrays.toString(eventCommandArray), Arrays.toString(Parser.parse(eventCommand)));
    }

    @Test
    public void parseIndex_intString_integer() {
        assertEquals(23, Parser.parseIndex("23"));
    }
}

package winston;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    Parser parser = new Parser(new TaskList(new ArrayList<>()));
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }
    
    @Test
    public void parseTest() {
        Command c = parser.parse("deadline test1 /by 1999-09-19");
        assertTrue(c instanceof AddDeadlineCommand);
    }

    @Test
    public void parseTest2() {
        Command c = parser.parse("event test2 /at 1999/12");
        assertTrue(c instanceof InvalidCommand);
    }
}

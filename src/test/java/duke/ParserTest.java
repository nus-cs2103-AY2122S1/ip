package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseUserInputTest(){
        String[] parsed = Parser.parseUserInput("done 1");
        assertEquals(parsed[0], "done");
        assertEquals(parsed[1], "1");
    }

    @Test
    public void parseArgsTest(){
        String[] parsed = Parser.parseArgs("Test Task Name /by 2021-01-01", "/by");
        assertEquals(parsed[0], "Test Task Name");
        assertEquals(parsed[1], "2021-01-01");
    }
    
    @Test
    public void parseStorage(){
        String[] parsed = Parser.parseStorage("event|Test Task Name/at2021-01-01|1");
        assertEquals(parsed[0], "event");
        assertEquals(parsed[1], "Test Task Name/at2021-01-01");
        assertEquals(parsed[2], "1");
    }
}

package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void Test1(){
        String test = "BYE";
        assertEquals("bye", Parser.parseCommand(test));
    }

    @Test
    public void Test2(){
        String test = "LiSt";
        assertEquals("list", Parser.parseCommand(test));
    }
}

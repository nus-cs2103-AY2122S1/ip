package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void emptyTextTest() {
        Parser p = new Parser();
        String[] output = p.inputParser("");
        assertEquals("", output[0]) ;
    }

    @Test
    public void validTextTest() {
        Parser p = new Parser();
        String[] output = p.inputParser("todo test text");

        assertEquals("todo", output[0]) ;
        assertEquals("test text", output[1]) ;
    }
}

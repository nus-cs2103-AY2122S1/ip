package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {

    @Test
    public void inputParser_emptyText_arrayOfEmptyStrings() {
        Parser p = new Parser();
        String[] output = p.inputParser("");
        assertEquals("", output[0]);
    }


    @Test
    public void inputParser_validText_arrayOfCommandAndParameter() {
        Parser p = new Parser();
        String[] output = p.inputParser("todo test text");

        assertEquals("todo", output[0]);
        assertEquals("test text", output[1]);
    }
}

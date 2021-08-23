package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void emptyTextTest() {
        Parser p = new Parser();
        assertEquals(new String[]{"",""}, p.inputParser(""));
    }

    @Test
    public void validTextTest() {
        Parser p = new Parser();
        assertEquals(new String[]{"todo","test text"}, p.inputParser("todo test text"));
    }
}

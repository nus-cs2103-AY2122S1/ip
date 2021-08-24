package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParserDeadlineDescriptionOutput() {
        String expected = "homework";
        String output = new Parser().deadlineInputTaskParser("deadline homework /by 2359");
        assertEquals(expected, output);
    }

    @Test
    public void testParserDeadlineDateOutput() {
        String expected = "2359";
        String output = new Parser().deadlineInputDateParser("deadline homework /by 2359");
        assertEquals(expected, output);
    }

    @Test
    public void testParserEventDescriptionOutput() {
        String expected = "birthday party";
        String output = new Parser().eventInputTaskParser("event birthday party /at 1900");
        assertEquals(expected, output);
    }

    @Test
    public void testParserEventDateOutput() {
        String expected = "20/12/20 1900";
        String output = new Parser().eventInputDateParser("event birthday party /at 20/12/20 1900");
        assertEquals(expected, output);
    }
}

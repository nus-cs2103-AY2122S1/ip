package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;


public class DukeTest {

    private final PrintStream stdout = System.out;
    private final InputStream stdin = System.in;

    public void resetStreams() {
        System.setIn(stdin);
        System.setOut(stdout);
    }

    @Test
    public void byeTest() {
        String data = "bye\n";
        String output = new Duke("data/test.txt").getResponse("bye");
        String expected = ("See you again, meow!\n").replaceAll("\n", "").replaceAll("\r", "");

        assertEquals(expected, output.replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    public void eventTest() {
        String data = "event christmas day /at 25Dec";
        String output = new Duke("data/test.txt").getResponse(data);
        String expected = ("Meow. I've added this task:\n"
                + "   [E][ ] christmas day (at: Dec 25 2021 All day)\n"
                + "Now you have 1 tasks in the list.\n").replaceAll("\n", "").replaceAll("\r", "");

        assertEquals(expected, output.replaceAll("\n", "").replaceAll("\r", ""));
    }

}

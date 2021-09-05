package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DukeTest {

    @Test
    public void byeTest() {
        String data = "bye\n";
        String output = new Duke("data/test.txt").getResponse(data);
        String expected = ("See you again, meow!\n")
                .replaceAll("\n", "")
                .replaceAll("\r", "");
        assertEquals(expected, output.replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    public void eventTest() {
        String data = "event christmas day /at 25Dec";
        String output = new Duke("data/test.txt").getResponse(data);

        String expected = ("Meow. I've added this task:\n"
                + "   [E][ ] christmas day (at: Dec 25 2021 All day)\n"
                + "Now you have 4 tasks in the list.\n").replaceAll("\n", "")
                .replaceAll("\r", "");

        assertEquals(expected, output.replaceAll("\n", "").replaceAll("\r", ""));
    }

}

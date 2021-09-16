package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class DukeTest {
    private final String divider = "________________________________";

    @Test
    public void testTodo() {
        String userInput = "todo eat";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        String expected = divider + "\r\nHello i is Duke\nWhat u want?\r\n" + divider + "\r\n"
                + divider + "\r\none more thing: [T][ ] eat\nNow you got 1 thing(s). sian\r\n" + divider + "\r\n"
                + divider + "\r\ni zao first\r\n" + divider;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        Duke.main(null);
        assertEquals(expected, out.toString().trim());
    }

    @Test
    public void testWrongInput() {
        String userInput = "wrong input";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        String expected = divider + "\r\nHello i is Duke\nWhat u want?\r\n" + divider + "\r\n"
                + divider + "\r\ncan type properly pls?\r\n" + divider + "\r\n"
                + divider + "\r\ni zao first\r\n" + divider;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        Duke.main(null);
        assertEquals(expected, out.toString().trim());

    }

    @Test
    public void testDelete() {
        String userInput = "delete 1";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        String expected = divider + "\r\nHello i is Duke\nWhat u want?\r\n" + divider + "\r\n"
                + divider + "\r\nthis one no more liao ah :\n[T][ ] eat\nNow you got 0 thing(s). sian\r\n"
                + divider + "\r\n" + divider + "\r\ni zao first\r\n" + divider;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        Duke.main(null);
        assertEquals(expected, out.toString().trim());
    }
}

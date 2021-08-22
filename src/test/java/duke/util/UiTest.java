package duke.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    // referenced from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testShowLine() {
        Ui.showLine();
        assertEquals("    ____________________________________________________________\n",
                outContent.toString());
    }

    @Test
    public void testNewLine() {
        Ui.newLine();
        assertEquals("\n", outContent.toString());
    }

    @Test
    public void testDisplayMessage() {
        Ui.displayMessage(new String[] {"Hi", "Test"});
        assertEquals("     Hi\n     Test\n", outContent.toString());
    }
}
package duke.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    //@@author dfa
    //Reused from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    // with minor modifications
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
    //@@author

    @Test
    public void testShowLine() {
        Ui.printLineSeparator();
        assertEquals("    ____________________________________________________________\n",
                outContent.toString());
    }

    @Test
    public void testNewLine() {
        Ui.printEmptyLine();
        assertEquals("\n", outContent.toString());
    }

    @Test
    public void testDisplayMessage() {
        Ui.displayMessage(new String[] {"Hi", "Test"});
        assertEquals("     Hi\n     Test\n", outContent.toString());
    }
}
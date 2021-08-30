package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.FileWritingException;

public class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final String horizontalLine = "____________________________________________________________";

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
    public void testPrintMessage() {
        Ui ui = new Ui();
        ui.printMessage("Test123");
        assertEquals(horizontalLine + "\nTest123\n" + horizontalLine + "\n", outContent.toString());
    }

    @Test
    public void testPrintErrorMessage() {
        Ui ui = new Ui();
        ui.printErrorMessage(new FileWritingException());
        assertEquals(horizontalLine
                + "\nHi, Duke ran into an error trying to save task to your hard drive. Please try again!\n"
                + horizontalLine
                + "\n", outContent.toString());
    }

}

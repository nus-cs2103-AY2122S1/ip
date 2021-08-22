package duke.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    // Referenced from http://www.mastertheboss.com/various-stuff/testing-java/how-to-verify-the-console-output-in-junit-tests/
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final Ui ui = new Ui();
    private final String ls = System.lineSeparator();

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
    public void prettyPrintTest() {
        Ui.prettyPrint("test string");
        assertEquals("\t-------------------------------------------------------------------------" + ls +
                        "\ttest string" + ls +
                        "\t-------------------------------------------------------------------------" + ls,
                outContent.toString());
    }

    @Test
    public void printExitMessageTest() {
        ui.printExitMessage();
        assertEquals("\t-------------------------------------------------------------------------" + ls +
                        "\tBye bye! See you again soon!" + ls +
                        "\t-------------------------------------------------------------------------" + ls,
                outContent.toString());
    }

    @Test
    public void printException() {
        ui.printException("TestException");
        assertEquals("\t" + "TestException" + ls,
                errContent.toString());
    }
}

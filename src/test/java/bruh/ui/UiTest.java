package bruh.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bruh.command.CommandStub;

public class UiTest {
    private static final ByteArrayOutputStream redirectedOut = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @AfterAll
    public static void restoreStream() {
        System.setOut(originalOut);
    }

    @BeforeAll
    public static void setUpStream() {
        System.setOut(new PrintStream(redirectedOut));
    }

    @AfterEach
    public void clearStream() {
        redirectedOut.reset();
    }

    @Test
    public void testShowCommandDescription() {
        new Ui().showCommandDescription(new CommandStub());

        assertEquals(
                "    ____________________________________________________________\n"
                        + "     " + "Test command description" + '\n'
                        + "    ____________________________________________________________\n\n",
                redirectedOut.toString().replace("\r", "")
        );
    }

    @Test
    public void testShowErrorMessage() {
        String errorMessage = "Error message";
        String expectedErrorMessage = "    ____________________________________________________________\n"
                + "     " + errorMessage + '\n'
                + "    ____________________________________________________________\n\n";

        new Ui().showErrorMessage(errorMessage);

        assertEquals(expectedErrorMessage, redirectedOut.toString().replace("\r", ""));
    }
}

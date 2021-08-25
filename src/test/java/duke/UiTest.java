package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * This class encapsulates a unit test for the Ui class.
 *
 * @author Kleon Ang
 */
public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final Ui ui = new Ui();

    /**
     * Set the output stream to a PrintStream before every test.
     */
    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Set the output stream back to System.out after testing.
     */
    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
    }

    /**
     * Test the printReply method for a valid given input.
     */
    @Test
    public void printReply_validInput_printsToConsole() {
        Ui.printReply("This is a test of printReply.");
        String expectedOut = "\t____________________________________________________________\n"
                + "\t This is a test of printReply."
                + "\n\t____________________________________________________________\n";
        assertEquals(expectedOut, outContent.toString());
    }

    /**
     * Test the showLoadingError method for a given fileName.
     */
    @Test
    public void showLoadingError_fileNotFound_printsErrorMessage() {
        ui.showLoadingError("noSuchFile.txt");
        String expectedOut = "\t____________________________________________________________\n"
                + "\t noSuchFile.txt not found. File has been created."
                + "\n\t____________________________________________________________\n";
        assertEquals(expectedOut, outContent.toString());
    }
}

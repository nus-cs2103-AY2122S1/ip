package pilcrow;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    Ui ui = new Ui();

    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printDeleteTaskMessage_givenIndex_printDeleteTaskMessage() {
        setUp();
        int index = 3;
        ui.printDeleteTaskMessage(index);
        assertEquals("Task " + index + " has been deleted.", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void printException_givenException_printException() {
        setUp();
        Exception exception = new Exception("Random exception.");
        ui.printException(exception);
        assertEquals("java.lang.Exception: Random exception.", outputStreamCaptor.toString()
                .trim());
    }
}

package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents testing the DukeException class.
 */
public class DukeExceptionTest {

    /**
     * Tests the getMessage method.
     */
    @SuppressWarnings("checkstyle:MethodName")
    @Test
    public void getMessage_StringOutOfBoundsException_CommandIncompleteMessage() {
        DukeException dukeException = new DukeException(DukeException.Exceptions.StringIndexOutOfBoundsException);
        assertEquals(dukeException.getMessage(),
            "☹ The duke.task description or duke.command is incomplete.:$\n");
    }
    @SuppressWarnings("checkstyle:MethodName")
    @Test
    public void getMessage_IllgealArgumentException_DoNotUnderstandMessage() {
        DukeException dukeException = new DukeException(DukeException.Exceptions.EXCEPTIONS);
        assertEquals(dukeException.getMessage(), "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
    public static void main(String[] args) {
    }
}

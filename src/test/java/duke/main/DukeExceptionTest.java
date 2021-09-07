package duke.main;

<<<<<<< HEAD:src/test/java/duke/main/DukeExceptionTest.java
import static org.junit.jupiter.api.Assertions.assertEquals;

=======
>>>>>>> branch-A-Level-10:src/test/java/IP/duke/main/DukeExceptionTest.java
import org.junit.jupiter.api.Test;

/**
 * Represents testing the DukeException class.
 */
public class DukeExceptionTest {

    /**
     * Tests the getMessage method.
     */
    @Test
    public void getMessage_StringOutOfBoundsException_CommandIncompleteMessage() {
        StringIndexOutOfBoundsException e = new StringIndexOutOfBoundsException();
        DukeException dukeException = new DukeException(e);
        assertEquals(dukeException.getMessage(), "☹ The duke.task description or duke.command is incomplete.:$\n" );
    }
    @Test
    public void getMessage_IllgealArgumentException_DoNotUnderstandMessage() {
        IllegalArgumentException e = new IllegalArgumentException();
        DukeException dukeException = new DukeException(e);
        assertEquals(dukeException.getMessage(), "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
    public static void main(String[] args) {
    }
}
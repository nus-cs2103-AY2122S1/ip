package duke.main;

import duke.main.Duke;
import duke.main.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents testing the DukeException class.
 */
public class DukeExceptionTest {

    /**
     * Tests the getMessage method.
     */
    @Test
    public void getMessage_StringOutOfBoundsException_CommandIncompleteMessage(){
        StringIndexOutOfBoundsException e = new StringIndexOutOfBoundsException();
        DukeException dukeException = new DukeException(e);
        assertEquals(dukeException.getMessage(), "☹ The task description or command is incomplete.:$\n");
    }
    
    @Test
    public void getMessage_IllgealArgumentException_DoNotUnderstandMessage(){
        IllegalArgumentException e = new IllegalArgumentException();
        DukeException dukeException = new DukeException(e);
        assertEquals(dukeException.getMessage(), "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
    
    public static void main(String[] args) {
        
    }
}

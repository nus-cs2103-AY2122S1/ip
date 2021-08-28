package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {
    @Test
    public void deadline_invalidInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            new Deadline("deadline holder /");
        });
    }

    @Test
    public void toString_normalInput_normalOutput() throws DukeException {
        assertEquals(String.format("[D][%s] %s (by: %s)",
                " ", "holder", "Mar 28 1998"), new Deadline("deadline holder / 1998-03-28").toString());
    }
}

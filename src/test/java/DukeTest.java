import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Task;
import duke.ToDo;

class DukeTest {

    @Test
    void testToString() {
        Task dummy = new ToDo("Walk in the park");
        assertEquals("[T][ ] Walk in the park", dummy.toString());
    }
}

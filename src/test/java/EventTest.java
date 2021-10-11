import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.Test;

import duke.Event;
import duke.Task;

class EventTest {

    @Test
    void testToString() {
        Task dummy = new Event("Walk in the park", "10-12-2022");
        assertEquals("[E][ ] Walk in the park(10-12-2022)", dummy.toString());
    }

}

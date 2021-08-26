package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Task dummy = new Event("Walk in the park","10-12-2022");
        assertEquals("[E][ ] Walk in the park(10-12-2022)",dummy.toString());
    }

}
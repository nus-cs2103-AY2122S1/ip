package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToString() {
        Task dummy = new ToDo("Walk in the park");
        assertEquals("[T][ ] Walk in the park",dummy.toString());
    }
}
package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void testToString() {
        ToDo testToDo = new ToDo("test");
        assertEquals("[T][ ] test", testToDo.toString());
    }
}
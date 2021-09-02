package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodosTest {

    @Test
    public void testToString() {
        String desc = "finish iP!";
        Todo todos = new Todo(desc);
        assertEquals("[T][ ] finish iP!", todos.toString());
    }

    @Test
    public void testToSaveString() {
        String desc = "finish iP!";
        Todo todos = new Todo(desc);
        assertEquals("T|0|finish iP!", todos.toSaveString());
    }

}

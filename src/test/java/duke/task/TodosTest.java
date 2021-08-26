package duke.task;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    @Test
    public void testToString() {
        String desc = "finish iP!";
        Todos todos = new Todos(desc);
        assertEquals("[T][ ] finish iP!", todos.toString());
    }

    @Test
    public void testToSaveString() {
        String desc = "finish iP!";
        Todos todos = new Todos(desc);
        assertEquals("T|0|finish iP!", todos.toSaveString());
    }

}
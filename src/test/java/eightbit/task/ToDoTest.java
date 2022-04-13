package eightbit.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToDoTest {

    private ToDo toDo;

    @BeforeEach
    void setUp() {
        toDo = new ToDo("NewToDo");
    }

    @Test
    void testToString() {
        String expected = "[T][ ] NewToDo";
        assertEquals(expected, toDo.toString());
    }

    @AfterEach
    void tearDown() {
        toDo = null;
    }
}

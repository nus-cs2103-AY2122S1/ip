package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.items.ToDo;

public class ToDoTest {
    @Test
    void ToDoPrintTest() {
        ToDo t1 = new ToDo("thing");
        assertEquals(t1.toString(), "[T][] thing");
    }

    @Test
    void ToDoDonePrintTest() {
        ToDo t2 = new ToDo("thing");
        t2.markAsDone();
        assertEquals(t2.toString(), "[T][X] thing");
    }
}
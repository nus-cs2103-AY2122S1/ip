package duketest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Todo;

public class TodoTest {
    @Test
    public void toTxtTest() {
        Todo todo = new Todo("mop the floor");
        assertEquals("T | 0 | mop the floor", todo.toTxt());
    }
}

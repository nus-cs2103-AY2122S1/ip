package duketest;

import duke.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void toTxtTest() {
        Todo todo = new Todo("mop the floor");
        assertEquals("T | 0 | mop the floor", todo.toTxt());
    }
}

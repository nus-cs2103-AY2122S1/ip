package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

public class TodoTest {
    @Test
    public void testOutput1() {
        Todo todo = new Todo("merge branch", true);
        assertEquals("[T][X] merge branch", todo.toString());
    }

    @Test
    public void testOutput2() {
        Todo todo = new Todo("merge branch", false);
        assertEquals("[T][  ] merge branch", todo.toString());
    }

    @Test
    public void testTaskToString() {
        Todo todo = new Todo("merge branch", false);
        assertEquals("T ~#~ 0 ~#~ merge branch ~#~ ", todo.convertTaskToString());
    }
}

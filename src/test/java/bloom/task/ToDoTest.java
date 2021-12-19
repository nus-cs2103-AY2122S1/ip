package bloom.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void newToDoTest() {
        ToDo t = new ToDo("sample");
        assertEquals("sample", t.description);
        assertFalse(t.isDone);
    }

    @Test
    public void toStringTest() {
        ToDo t = new ToDo("sample");
        assertEquals("[T][ ] sample", t.toString());
    }

    @Test
    public void toDbTest() {
        ToDo t = new ToDo("sample");
        assertEquals("T | 0 | sample", t.toDb());
    }
}

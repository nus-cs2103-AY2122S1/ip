package aoi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void statusIconTest() {
        Task todo = new Todo("do homework", true);
        assertEquals("[X]", todo.getStatusIcon());
    }

    @Test
    public void toStringTest() {
        Task todo = new Todo("do homework", true);
        assertEquals("[T][X] do homework\n  Notes: ", todo.toString());
    }

    @Test
    public void saveFormatTest() {
        Task todo = new Todo("do homework", true);
        assertEquals("T | 1 | do homework | ", todo.printInSaveFormat());
    }
}

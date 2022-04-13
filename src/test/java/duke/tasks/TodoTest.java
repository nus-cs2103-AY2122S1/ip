package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void showTaskTest() {
        Todo t1 = new Todo("running", true);
        assertEquals("[T][✗] running", t1.showTask());
    }

    @Test
    public void saveTaskTest() {
        Todo t1 = new Todo("dancing", false);
        assertEquals("T | 0 | dancing", t1.saveTask());
    }
}
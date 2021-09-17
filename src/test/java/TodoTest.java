import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dino.task.Todo;

public class TodoTest {

    @Test
    public void testSampleInput() {
        Todo test = new Todo("homework");
        assertEquals("[T][ ] homework", test.toString());
    }

    @Test
    public void testDoneInput() {
        Todo test = new Todo("homework", true);
        assertEquals("[T][X] homework", test.toString());
    }

    @Test
    public void testDescription() {
        Todo test = new Todo("homework");
        assertEquals("homework", test.getDescription());
    }

}

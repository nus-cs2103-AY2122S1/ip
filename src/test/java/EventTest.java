import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dino.task.Event;

public class EventTest {
    private Event test = new Event("homework", "29/01/1999 1pm");

    @Test
    public void testSampleInput() {
        Event test = new Event("homework", "29/01/1999 1pm");
        assertEquals("[E][ ] homework (at: 29/01/1999 1pm)", test.toString());
    }

    @Test
    public void testDoneInput() {
        Event test = new Event("homework", "29/01/1999 1pm", true);
        assertEquals("[E][X] homework (at: 29/01/1999 1pm)", test.toString());
    }

    @Test
    public void testDescription() {
        assertEquals("homework", test.getDescription());
    }

    @Test
    public void testDate() {
        assertEquals("29/01/1999 1pm", test.getDueDate());
    }

}

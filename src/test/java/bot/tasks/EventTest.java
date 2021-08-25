package bot.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private final String description = "Dinner";
    private final String standardTime = "22/8/2021 7pm";
    private final String ymdTime = "2021/8/22 7pm";
    private final Event e1 = new Event(description, standardTime);
    private final Event e2 = new Event(description, ymdTime);

    @Test
    public void descriptionTest() {
        assertEquals(description, e1.getDescription());
        assertEquals(description, e2.getDescription());
    }

    @Test
    public void timingTest() {
        assertEquals("22 August 2021, 7pm", e1.getTiming());
        assertEquals("22 August 2021, 7pm", e2.getTiming());
    }

    @Test
    public void toStringTest() {
        assertEquals("[E]" + "[" + (e1.isDone ? "X" : " ") + "] " + "Dinner (at: 22 August 2021, 7pm)", e1.toString());
        assertEquals("[E]" + "[" + (e1.isDone ? "X" : " ") + "] " + "Dinner (at: 22 August 2021, 7pm)", e1.toString());
    }
}

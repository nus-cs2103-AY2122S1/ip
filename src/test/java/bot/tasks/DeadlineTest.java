package bot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final String description = "Finish A-Junit";
    private final String standardTime = "22/8/2021 6pm";
    private final String ymdTime = "2021/8/22 6pm";
    private final Deadline d1 = new Deadline(description, standardTime);
    private final Deadline d2 = new Deadline(description, ymdTime);

    @Test
    public void descriptionTest() {
        assertEquals(description, d1.getDescription());
        assertEquals(description, d2.getDescription());
    }

    @Test
    public void byTest() {
        assertEquals("22 August 2021, 6pm", d1.getBy());
        assertEquals("22 August 2021, 6pm", d2.getBy());
    }

    @Test
    public void toStringTest() {
        assertEquals("[D]" + "[" + (d1.isDone ? "X" : " ") + "] " + "Finish A-Junit (by: 22 August 2021, 6pm)",
                d1.toString());
        assertEquals("[D]" + "[" + (d1.isDone ? "X" : " ") + "] " + "Finish A-Junit (by: 22 August 2021, 6pm)",
                d1.toString());
    }
}

package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[] buy food", new Task("buy food").toString());
    }
    @Test
    public void markCompleteTask_userInput_success() {
        Task t = new Task("buy food");
        t.setDone();
        assertEquals("[X] buy food", t.toString());
    }


}

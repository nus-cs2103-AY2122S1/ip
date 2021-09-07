package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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

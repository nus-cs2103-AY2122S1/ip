package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void createTest() {
        Event e = new Event("test", "10 Aug");
        assertEquals("[E][ ] test (at: 10 Aug)", e.toString());
    }
}

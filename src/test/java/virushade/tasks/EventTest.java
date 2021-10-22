package virushade.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringTest() {
        assertEquals("[E][x] EventTask /at 12:05AM",
                new Event("EventTask", "0005", true).toString());
    }
}

package chadbot.task;

import chadbot.ChadException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void Event_missingDateTime_chadExceptionThrown(){
        try {
            assertEquals(0, new Event(new String[]{"test"}));
            fail(); // the test should not reach this line
        } catch (ChadException e) {
            assertEquals("Usage of event does not match 'description' /at 'timeframe'", e.toString());
        }
    }
}

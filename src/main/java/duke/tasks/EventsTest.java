package duke.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EventsTest {
    @Test
    public void testGetSymbol() {
        Events events = new Events("testGetSymbol description", "testGetSymbol dayTime");

        assertEquals("E", events.getSymbol());
    }

    @Test
    public void testDayTime() {
        Events events = new Events("testDayTime description", "testDayTime dayTime");

        assertEquals("testDayTime dayTime", events.getDayTime());
    }

    @Test
    public void testMarkAsDone() {
        Events events = new Events("testMarkAsDone description", "testMarkAsDone dayTime");

        assertEquals(true, events.markAsDone().getIsDone());
    }

    @Test
    public void testToString() {
        Events events = new Events("testToString description", "testToString dayTime");

        assertEquals("[E][ ] testToString description (at: testToString dayTime)", events.toString());
    }
}

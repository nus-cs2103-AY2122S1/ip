package chadbot.data;

import chadbot.ChadException;
import chadbot.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CalendarTest {

    class EventStub extends Event {
        public EventStub() throws ChadException {
            super(new String[]{"test", "2015-02-20T06:30"});
        }
    }


    @Test
    public void getEventsAt_getEvents_eventCorrectlyRetrieved() {
        try {
            LocalDateTime t = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
            Calendar c = new Calendar();
            c.add(new EventStub());
            assertEquals(t, c.getEventsAt(t).get(0).getDateTime());
        } catch (Exception e) {
            fail();
        }
    }
}

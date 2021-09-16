package meap.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private static final String TIME_IN_FUTURE1 = "12-12-2025 2359";
    private static final String TIME_IN_FUTURE2 = "12-12-2026 2359";


    @Test
    public void equalsMtd_sameObj_returnTrue() {
        Event event1 = Event.of("Eat a potato", TIME_IN_FUTURE1);
        Event event2 = Event.of("Eat a potato", TIME_IN_FUTURE1);
        assertEquals(event1.equals(event2), true);
    }

    @Test
    public void equalsMtd_sameTypeDiffDesc_returnFalse() {
        Event event1 = Event.of("Eat a potato", TIME_IN_FUTURE1);
        Event event2 = Event.of("Eat a banana", TIME_IN_FUTURE1);
        assertEquals(event1.equals(event2), false);
    }

    @Test
    public void equalsMtd_sameTypeDiffTime_returnFalse() {
        Event event1 = Event.of("Eat a potato", TIME_IN_FUTURE1);
        Event event2 = Event.of("Eat a potato", TIME_IN_FUTURE2);
        assertEquals(event1.equals(event2), false);
    }

    @Test
    public void equalsMtd_diffTaskObj_returnFalse() {
        Event event1 = Event.of("Eat a banana", TIME_IN_FUTURE1);
        ToDo toDo1 = ToDo.of("Eat a potato");
        assertEquals(event1.equals(toDo1), false);
    }
}
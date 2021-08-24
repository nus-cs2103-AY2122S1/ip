package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventTest_createInstance_sameStringRep() {
        Event event = new Event("Go for concert", LocalDate.of(2021,8,1));
        assertEquals(event.toString(),"[E][ ] Go for concert(at: AUGUST 1 2021)");
    }
}
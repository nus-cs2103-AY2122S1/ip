package kermit.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testGetShortForm(){
        assertEquals("E", new Event("Test text", LocalDate.parse("2021-04-05")).getShortForm());
    }
}

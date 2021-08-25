package kermit.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testGetShortForm(){
        assertEquals("E", new ToDos("Test text").getShortForm());
    }
}

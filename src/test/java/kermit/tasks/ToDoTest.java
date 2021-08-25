package kermit.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testGetShortForm(){
        assertEquals("T", new ToDos("Test text").getShortForm());
    }
}


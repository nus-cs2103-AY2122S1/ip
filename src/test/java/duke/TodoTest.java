package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] work", new ToDo("work").toString());
    }

    @Test
    public void printOutCome_sleepInput_success() {
        assertEquals("T,0,sleep", new ToDo("sleep").print());
    }
}
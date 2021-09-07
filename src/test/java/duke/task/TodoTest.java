package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void fileFormat_normalNoInput_properFormat() {
        assertEquals("T | 0 | buy bread", new Todo("buy bread").fileFormat());
    }

    @Test
    public void toString_normalNoInput_expectedString() {
        assertEquals("[T][ ] buy bread", new Todo("buy bread").toString());
    }
}

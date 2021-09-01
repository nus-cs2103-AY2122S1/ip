package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

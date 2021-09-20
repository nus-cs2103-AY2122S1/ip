package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void fileFormat_normalNoInput_properFormat() {
        assertEquals("T | 0 | H | buy bread", new Todo("buy bread", "H").convertToFileFormat());
    }

    @Test
    public void toString_normalNoInput_expectedString() {
        assertEquals("[T][ ][H] buy bread", new Todo("buy bread", "H").toString());
    }
}

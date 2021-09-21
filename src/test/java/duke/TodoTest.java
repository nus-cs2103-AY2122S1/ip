package duke;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] do assignment", new Todo("do assignment").toString());
    }
}

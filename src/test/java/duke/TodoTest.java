package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        assertEquals("[T][ ] do assignment", new Todo("do assignment").toString());
    }
}

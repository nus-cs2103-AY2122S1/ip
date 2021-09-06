package duchess.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testFileFormat() {
        ToDo t = new ToDo("test");
        assertEquals(t.toFileFormat(), "Ttest,false");
        t.setDone(true);
        assertEquals(t.toFileFormat(), "Ttest,true");
    }
}

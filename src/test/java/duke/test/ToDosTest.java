package duke.test;
import duke.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void testWriteTask() {
        ToDos t = new ToDos("Task 1");
        assertEquals("T | 0 | Task 1", t.writeTask());
    }


}


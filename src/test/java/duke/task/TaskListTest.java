package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void testGenerateStore() {
        String[] input = new String[]{"T - 1 - sth", "E - 1 - sth3 - 2020-12-12", "D - 1 - sth2 - 2020-12-12"};
        TaskList tl = new TaskList(input);
        assertEquals(tl.get(0).toString(), "[T][X] sth");
        assertEquals(tl.get(1).toString(), "[E][X] sth3(at: Dec 12 2020)");
        assertEquals(tl.get(2).toString(), "[D][X] sth2(by: Dec 12 2020)");
    }
}

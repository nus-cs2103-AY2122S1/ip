package duke;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    String s = "event swimming /on Monday 2pm";
    String[] arr = s.split(" ");


    @Test
    public void testGetDescription() {
        assertEquals("swimming ",TaskList.getDescription(arr));
    }

    @Test
    public void testGetDeadline() {
        assertEquals("on: Monday 2pm", TaskList.getDeadline(arr));
    }

    @Test
    public void testGetSize() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertEquals(0, taskList.getSize());
    }

}

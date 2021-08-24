package duke;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
}

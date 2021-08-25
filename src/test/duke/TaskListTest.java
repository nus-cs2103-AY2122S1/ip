package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void testGetSizeOfOne() {
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(new Task("taskTest"));
        assertEquals(1, new TaskList(arr).getSize());
    }

    @Test
    void testGetSizeOfZero() {
        assertEquals(0, new TaskList().getSize());
    }
}

package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TaskListTest {

    @Test
    void testGetSizeOfOne() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("taskTest"));
        assertEquals(1, new TaskList(tasks).getSize());
    }

    @Test
    void testGetSizeOfZero() {
        assertEquals(0, new TaskList().getSize());
    }
}

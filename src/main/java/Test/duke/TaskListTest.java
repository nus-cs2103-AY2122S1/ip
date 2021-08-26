package duke;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void testToString() {
        TaskList dummy = new TaskList();
        dummy.addTask(new Event("Walk in the park","10-12-2022"));
        assertEquals(1,dummy.size());
        dummy.removeTask(0);
        assertEquals(0,dummy.size());
    }

}
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.tasks.TaskList;
import duke.tasks.Todo;

class TaskListTest {

    @Test
    void getTaskCount_emptyList_success() {
        TaskList testList = new TaskList();
        assertEquals(0, testList.getTaskCount());
    }

    @Test
    void getTaskCount_filledList_success() {
        TaskList testList = new TaskList();
        for (int i = 0; i < 5; i++) {
            testList.addTask(new Todo("make bread", false));
        }
        assertEquals(5, testList.getTaskCount());
    }

    @Test
    void isListEmpty_emptyList_success() {
        assertTrue(new TaskList().isListEmpty());
    }

    @Test
    void isListEmpty_filledList_success() {
        TaskList testList = new TaskList();
        testList.addTask(new Todo("make coffee", false));
        assertFalse(testList.isListEmpty());
    }
}

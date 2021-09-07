package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.tasks.Todo;

class TaskArrayListTest {


    @Test
    void addTask_increaseSize() {
        TaskArrayList taskList = new TaskArrayList();
        assertEquals(0, taskList.size());
        taskList.addTask(new Todo("ok"));
        assertEquals(1, taskList.size());
    }

    @Test
    void deleteTask_decreaseSize() throws DukeException {
        TaskArrayList taskList = new TaskArrayList();
        taskList.addTask(new Todo("ok"));
        assertEquals(1, taskList.size());
        taskList.deleteTask(1);
        assertEquals(0, taskList.size());
    }


    @Test
    void markDone() throws DukeException {
        TaskArrayList taskList = new TaskArrayList();
        taskList.addTask(new Todo("ok"));

        assertFalse(taskList.get(0).isTaskDone());
        taskList.markDone(1);
        assertTrue(taskList.get(0).isTaskDone());

    }

    @Test
    void enumerate() {
        String testString = "1. [T][ ] ok\n"
                + "2. [T][ ] ok\n";
        TaskArrayList taskList = new TaskArrayList();
        taskList.addTask(new Todo("ok"));
        taskList.addTask(new Todo("ok"));

        assertEquals(
                testString,
                taskList.enumerate()
        );
    }

    @Test
    void find() {
        String testString = "1. [T][ ] include\n"
                + "3. [T][ ] include\n";

        TaskArrayList taskList = new TaskArrayList();
        taskList.addTask(new Todo("include"));
        taskList.addTask(new Todo("exclude"));
        taskList.addTask(new Todo("include"));

        assertEquals(
                testString,
                taskList.find("in")
        );
    }
}

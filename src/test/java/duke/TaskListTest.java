package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private TaskList createTaskList() {
        ArrayList<String> array = new ArrayList<>();
        array.add("T,false,task 1");
        array.add("D,false,task 2 ,2021-08-24");
        array.add("E,true,task 3 ,2021-08-25");
        array.add("T,true,task 4");
        return new TaskList(array);
    }

    @Test
    public void testAddTest() {
        TaskList tasklist = new TaskList();
        assertEquals("T,false,task 1", tasklist.addToDo("task 1"));
    }

    @Test
    public void testAddTestWithDate() {
        assertEquals("D,false,task 1,2021-08-08",
                new TaskList().addDeadline("task 1", LocalDate.parse("2021-08-08")));
    }

    @Test
    public void testChangeTaskStatus() {
        TaskList tasklist = createTaskList();
        try {
            String s = tasklist.markAsDone(2);
            assertEquals("[D][X] task 2  (by: Aug 24 2021)\nNumber of tasks remaining: 4", s);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void changeTaskStatus_indexOutOfBounds_exceptionThrown() {
        TaskList tasklist = createTaskList();
        try {
            String s = tasklist.markAsDone(5);
            assertEquals("[D][X] task 2  (by: Aug 24 2021)\nNumber of tasks remaining: 4", s);
            fail(); //the test should not reach this line
        } catch (DukeException e) {
            assertEquals("Task does not exist. Use list to check all tasks available.", e.getMessage());
        }
    }

    @Test
    public void testDeleteTask() {
        TaskList tasklist = createTaskList();
        try {
            String s = tasklist.deleteTask(2);
            assertEquals("[D][ ] task 2  (by: Aug 24 2021)\nNumber of tasks remaining: 3", s);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        TaskList tasklist = createTaskList();
        try {
            String s = tasklist.markAsDone(5);
            assertEquals("[D][ ] task 2  (by: Aug 24 2021)\nNumber of tasks remaining: 3", s);
            fail(); //the test should not reach this line
        } catch (DukeException e) {
            assertEquals("Task does not exist. Use list to check all tasks available.", e.getMessage());
        }
    }
}

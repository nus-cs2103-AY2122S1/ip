package duke;

import duke.task.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read books", false));
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(new Todo("read books", false));
        assertEquals(expectedList, taskList.getTasks());
        taskList.addTask(new Deadline("project", false, LocalDateTime.parse("2021-08-25T23:59")));
        expectedList.add(new Deadline("project", false, LocalDateTime.parse("2021-08-25T23:59")));
        assertEquals(expectedList, taskList.getTasks());
        taskList.addTask(new Event("meeting", true, LocalDateTime.parse("2021-12-03T08:00")));
        expectedList.add(new Event("meeting", true, LocalDateTime.parse("2021-12-03T08:00")));
        assertEquals(expectedList, taskList.getTasks());
    }

    @Test
    public void testRemoveTask() {
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(new Todo("read books", false));
        expectedList.add(new Deadline("project", false, LocalDateTime.parse("2021-08-25T23:59")));
        expectedList.add(new Event("meeting", true, LocalDateTime.parse("2021-12-03T08:00")));
        TaskList taskList = new TaskList(expectedList);
        assertEquals(new Deadline("project", false, LocalDateTime.parse("2021-08-25T23:59")),
                taskList.removeTask(1));
        expectedList.remove(1);
        assertEquals(expectedList, taskList.getTasks());
        DukeException exception = assertThrows(DukeException.class, () -> {
            taskList.removeTask(3);
        });
        assertEquals("OOPS!!! Task number: 4 does not exist.", exception.getMessage());
    }

    @Test
    public void testMarkAsDone() {
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(new Todo("read books", false));
        expectedList.add(new Deadline("project", false, LocalDateTime.parse("2021-08-25T23:59")));
        expectedList.add(new Event("meeting", true, LocalDateTime.parse("2021-12-03T08:00")));
        TaskList taskList = new TaskList(expectedList);
        taskList.markTaskAsDone(1);
        expectedList.set(1, new Deadline("project", true, LocalDateTime.parse("2021-08-25T23:59")));
        assertEquals(expectedList, taskList.getTasks());
        DukeException exception = assertThrows(DukeException.class, () -> {
            taskList.markTaskAsDone(3);
        });
        assertEquals("OOPS!!! Task number: 4 does not exist.", exception.getMessage());
    }
}

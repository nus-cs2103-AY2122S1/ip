package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void add_addNewTodoTask_newToDoTask() {
        TaskList taskList = new TaskList();
        Task t = taskList.add(Task.TaskType.TODO, "todo", null, null);
        if (!(t instanceof Todo)) {
            fail();
        }
        assertEquals("todo", t.description);
        assertEquals(taskList.getTaskList().get(0), t);
    }

    @Test
    public void delete_deleteTask_taskDeleted() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Task t1 = new Deadline("ddl", LocalDate.of(2021, 1, 1));
            tasks.add(t1);
            TaskList taskList = new TaskList(tasks);
            Task t2 = taskList.delete(0);
            assertEquals(t1, t2);
            assertTrue(taskList.getTaskList().isEmpty());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void delete_taskIndexOutOfBound_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> taskList.delete(0));
    }
}
package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    
    @Test
    public void addToDoTask_success() {
        try {
            TaskList tasks = new TaskList();
            
            assertEquals(new ToDo("task").toString(), tasks.addTask(TaskList.TaskType.TODO, "task").toString());
            assertEquals(1, tasks.getListSize());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void addDeadlineTask_success() {
        try {
            TaskList tasks = new TaskList();

            assertEquals(new Deadline("task", "25-08-2021 14:30").toString(), tasks.addTask(TaskList.TaskType.DEADLINE, "task /by 25-08-2021 14:30").toString());
            assertEquals(1, tasks.getListSize());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void addEventTask_success() {
        try {
            TaskList tasks = new TaskList();

            assertEquals(new Event("task", "1pm-2pm").toString(), tasks.addTask(TaskList.TaskType.EVENT, "task /at 1pm-2pm").toString());
            assertEquals(1, tasks.getListSize());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_success() {
        try {
            TaskList tasks = new TaskList();
            tasks.addTask(TaskList.TaskType.TODO, "task");
            assertEquals(1, tasks.getListSize());
            
            tasks.deleteTask(1);
            assertEquals(0, tasks.getListSize());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markTaskDone_success() {
        try {
            TaskList tasks = new TaskList();
            Task task = tasks.addTask(TaskList.TaskType.TODO, "task");
            assertEquals("[T][ ] task", task.toString());

            tasks.markTaskDone(1);
            assertEquals("[T][X] task", task.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}

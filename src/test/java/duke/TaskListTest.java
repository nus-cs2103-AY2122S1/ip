package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void addTask_newTask_taskCanBeRetrieved() {
        TaskList taskList = new TaskList();

        assertEquals(0, taskList.getSize());

        Task newTask = new Todo("go back home");
        taskList.addTask(newTask);

        assertEquals(1, taskList.getSize());
        assertEquals(newTask, taskList.getTask(0));
    }

    @Test
    public void markTask_undoneTask_taskIsDone() throws DukeException {
        TaskList taskList = new TaskList();

        Task firstTask = new Deadline("assignment", "01/09/2021");
        taskList.addTask(firstTask);

        assertEquals(firstTask, taskList.getTask(0));

        // after marking both tasks as done, both tasks should still be equal
        firstTask.markAsDone();
        taskList.markTask(1);
        assertEquals(firstTask, taskList.getTask(0));
    }

    @Test
    public void deleteTask_taskCannotBeRetrieved() throws DukeException {
        TaskList taskList = new TaskList();

        Task firstTask = new Todo("go library");
        Task secondTask = new Event("concert", "03/08/2021");
        Task thirdTask = new Todo("borrow book");
        taskList.addTask(firstTask);
        taskList.addTask(secondTask);
        taskList.addTask(thirdTask);

        assertEquals(3, taskList.getSize());

        taskList.deleteTask(3);

        assertEquals(2, taskList.getSize());
        assertNotEquals(thirdTask, taskList.getTask(0));
        assertNotEquals(thirdTask, taskList.getTask(1));
    }

}

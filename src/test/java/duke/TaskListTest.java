package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        TaskList tasks = new TaskList();

        Task firstTask = new Deadline("assignment", "01/09/2021");
        tasks.addTask(firstTask);

        assertEquals(firstTask, tasks.getTask(0));

        // after marking both tasks as done, both tasks should still be equal
        firstTask.markAsDone();
        tasks.markTask(1);
        assertEquals(firstTask, tasks.getTask(0));
    }

    @Test
    public void deleteTask_taskCannotBeRetrieved() throws DukeException {
        TaskList tasks = new TaskList();

        Task firstTask = new Todo("go library");
        Task secondTask = new Event("concert", "03/08/2021");
        Task thirdTask = new Todo("borrow book");
        tasks.addTask(firstTask);
        tasks.addTask(secondTask);
        tasks.addTask(thirdTask);

        assertEquals(3, tasks.getSize());

        tasks.deleteTask(3);

        assertEquals(2, tasks.getSize());
        assertNotEquals(thirdTask, tasks.getTask(0));
        assertNotEquals(thirdTask, tasks.getTask(1));
    }

}

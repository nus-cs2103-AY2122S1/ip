package agent.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import agent.data.TaskStorageStub;
import agent.exceptions.InvalidTaskDataException;
import agent.exceptions.InvalidTaskNumberException;
import agent.exceptions.TaskFileIoException;

class TaskListTest {
    @Test
    void canGetAllTasksWhenNoTasks_noTasks_returnsEmptyList() throws TaskFileIoException, InvalidTaskDataException {
        TaskList taskList = new TaskList(new TaskStorageStub());
        assertEquals(new ArrayList<>(), taskList.getAllTasks());
    }

    @Test
    void canAddTasks_tasksToAdd_returnsListWithAddedTasks()
            throws InvalidTaskNumberException, TaskFileIoException, InvalidTaskDataException {
        TaskList taskList = new TaskList(new TaskStorageStub());
        Deadline deadline = new Deadline("deadline", true, LocalDate.parse("2021-12-12"));
        Event event = new Event("event", false, "home");
        ToDo todo = new ToDo("todo", false);

        taskList.addTask(deadline);
        assertEquals(1, taskList.getNumOfTasks());
        assertEquals(deadline, taskList.getTask(0));

        taskList.addTask(event);
        assertEquals(2, taskList.getNumOfTasks());
        assertEquals(event, taskList.getTask(1));

        taskList.addTask(todo);
        assertEquals(3, taskList.getNumOfTasks());
        assertEquals(todo, taskList.getTask(2));
    }

    @Test
    void canDeleteTasks_tasksAndTaskIndexToDelete_returnsListWithNoDeletedTasks()
            throws InvalidTaskNumberException, TaskFileIoException, InvalidTaskDataException {
        TaskList taskList = new TaskList(new TaskStorageStub());
        Deadline deadline = new Deadline("deadline", true, LocalDate.parse("2021-12-12"));
        Event event = new Event("event", false, "home");
        ToDo todo = new ToDo("todo", false);

        taskList.addTask(deadline);
        assertEquals(1, taskList.getNumOfTasks());
        assertEquals(deadline, taskList.getTask(0));
        taskList.deleteTask(0);

        taskList.addTask(event);
        taskList.addTask(todo);

        taskList.deleteTask(0);
        assertEquals(todo, taskList.getTask(0));
        assertEquals(1, taskList.getNumOfTasks());
        taskList.deleteTask(0);
        assertEquals(0, taskList.getNumOfTasks());
    }

    @Test
    void canMarkTaskAsDone_tasksAndTaskIndexToMarkDone_returnsListWithDoneTask()
            throws InvalidTaskNumberException, TaskFileIoException, InvalidTaskDataException {
        TaskList taskList = new TaskList(new TaskStorageStub());
        Deadline deadline = new Deadline("deadline", true, LocalDate.parse("2021-12-12"));
        Event event = new Event("event", false, "home");
        ToDo todo = new ToDo("todo", false);
        Event doneEvent = new Event("event", true, "home");
        ToDo doneTodo = new ToDo("todo", true);

        taskList.addTask(deadline);
        taskList.addTask(event);
        taskList.addTask(todo);

        assertEquals(event, taskList.getTask(1));
        assertEquals(todo, taskList.getTask(2));

        taskList.setDone(1);
        assertEquals(doneEvent.getTaskRepresentation(), event.getTaskRepresentation());
        taskList.setDone(2);
        assertEquals(doneTodo.getTaskRepresentation(), todo.getTaskRepresentation());
    }
}

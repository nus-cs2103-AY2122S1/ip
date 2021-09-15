package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.Todo;

class TaskListTest {

    private static final String DESCRIPTION = "task description";

    private TaskList getTaskListThreeTodos() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            taskArrayList.add(new Todo(DESCRIPTION));
        }
        return new TaskList(taskArrayList);
    }

    @Test
    void size_taskList_threeTodo() {
        assertEquals(3, getTaskListThreeTodos().size());
    }

    @Test
    void get_taskList_sameTodo() {
        Todo todo = new Todo(DESCRIPTION);

        assertEquals(todo, getTaskListThreeTodos().get(1));
    }

    @Test
    void add_taskListOfThreeTodos_taskListOfFourTodos() {
        TaskList tasks = getTaskListThreeTodos();
        Todo todo = new Todo(DESCRIPTION);
        Task other = tasks.add(todo);

        assertEquals(4, tasks.size());
        assertEquals(todo, other);
    }

    @Test
    void remove_taskListOfThreeTodos_taskListOfTwoTodos() {
        TaskList tasks = getTaskListThreeTodos();
        Todo todo = new Todo(DESCRIPTION);
        Task other = tasks.remove(1);

        assertEquals(2, tasks.size());
        assertEquals(todo, other);
    }

    @Test
    void equals_sameTaskList_true() {
        TaskList tasks = getTaskListThreeTodos();
        TaskList other = getTaskListThreeTodos();

        assertTrue(tasks.equals(other));
    }

    @Test
    void getTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            taskArrayList.add(new Todo(DESCRIPTION));
        }
        assertEquals(taskArrayList, getTaskListThreeTodos().getTasks());
    }
}

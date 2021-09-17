package poseidon.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import poseidon.task.Task;
import poseidon.task.Todo;

/**
 * Represents a testing class for {@code TaskList}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class TaskListTest {

    @Test
    public void taskListConstructor_newTaskList_holdEmptyArrayList() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getList().size());
    }

    @Test
    public void taskListConstructor_existingTaskList_holdGivenArrayList() {
        ArrayList<Task> arrayList = new ArrayList<>();
        int size = 5;
        for (int i = 0; i < size; i++) {
            arrayList.add(new Todo("new Todo object"));
        }
        TaskList taskList = new TaskList(arrayList);
        assertEquals(size, taskList.getList().size());
    }

    @Test
    public void addTask_newTasks_addCorrectTask() {
        TaskList taskList = new TaskList();
        ArrayList<Task> arrayListInsideTaskList = taskList.getList();
        Todo todo = new Todo("sample todo object");
        taskList.addTask(todo);
        assertEquals(todo, arrayListInsideTaskList.get(arrayListInsideTaskList.size() - 1));
    }
}

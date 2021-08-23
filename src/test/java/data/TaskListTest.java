package data;

import jwbot.data.TaskList;
import jwbot.data.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTaskTest() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("study");
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTask(0));
    }

}

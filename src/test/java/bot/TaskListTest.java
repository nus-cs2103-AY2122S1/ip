package java.bot;

import main.java.bot.TaskList;
import main.java.task.Task;
import main.java.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    ArrayList<Task> arrayList;
    TaskList taskList;

    @Test
    public void testGetSize() {
        arrayList.add(new TodoTask("Test1"));
        arrayList.add(new TodoTask("Test2"));
        arrayList.add(new TodoTask("Test3"));

        taskList.addTask(new TodoTask("Test1"));
        taskList.addTask(new TodoTask("Test2"));
        taskList.addTask(new TodoTask("Test3"));

        assertEquals(arrayList.size(), taskList.getSize());
    }

}

package duke;

import duke.task.TaskList;
import duke.task.Todo;

import org.junit.Test;
import org.junit.Assert;

public class TaskListTest {
    @Test
    public void addTask_taskObject_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo do assignment");
        taskList.addTask(todo);
        Assert.assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void removeTask_taskObjects_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo do assignment");
        Todo todo2 = new Todo("todo play sports");
        Todo todo3 = new Todo("todo sing song");
        
        taskList.addTask(todo);
        taskList.addTask(todo2);
        taskList.addTask(todo3);
        taskList.removeTask(1);
        
        Assert.assertEquals(todo3, taskList.getTask(1));
    }

    @Test
    public void size_taskObjects_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo do assignment");
        Todo todo2 = new Todo("todo play sports");
        Todo todo3 = new Todo("todo sing song");
        
        taskList.addTask(todo);
        taskList.addTask(todo2);
        taskList.addTask(todo3);
        
        Assert.assertEquals(3, taskList.getSize());
    }
}

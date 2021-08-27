package duke;
import duke.task.ToDoTask;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        Assert.assertEquals(new ToDoTask("read a book").toString() , temp.getTask(0).toString());
    }

    @Test
    public void deleteTaskAtIndexTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        Assert.assertEquals(temp.getTask(0).toString(), temp.deleteTaskAtIndex(0).toString());
    }

    @Test
    public void getTaskTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        Assert.assertEquals(new ToDoTask("read a book").toString(), temp.getTask(0).toString());
    }

    @Test
    public void getTaskListLengthTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        Assert.assertEquals(1, temp.getTaskListLength());
    }

    @Test
    public void completeTaskTest() {
        TaskList temp = new TaskList(new ArrayList<>());
        temp.addTask(new ToDoTask("read a book"));
        temp.getTask(0).markAsCompleted();
        Assert.assertEquals("[T][X] read a book", temp.getTask(0).toString());
    }
}
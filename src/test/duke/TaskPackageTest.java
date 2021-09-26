package duke;

import duke.task.TASK_TYPE;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskPackageTest {
    @Test
    public void TaskTest(){
        Task t = new Task("Sample");
        Assert.assertEquals("Sample", t.getDescription());
    }
    @Test
    public void TodoTest(){
        Task t = new Todo("Sample", TASK_TYPE.T);
        Assert.assertEquals("Sample", t.getDescription());
        Assert.assertEquals(TASK_TYPE.T, t.getType());
    }
    @Test
    public void DeadlineTest(){
        Task t = new Deadline("Sample", "2019-10-15", TASK_TYPE.D);
        Assert.assertEquals("Sample", t.getDescription());
        Assert.assertEquals(TASK_TYPE.D, t.getType());
        Assert.assertEquals(LocalDate.parse("2019-10-15"), t.getBy());
    }
    @Test
    public void EventTest(){
        Task t = new Event("Sample", "2019-10-15", TASK_TYPE.E);
        Assert.assertEquals("Sample", t.getDescription());
        Assert.assertEquals(TASK_TYPE.E, t.getType());
        Assert.assertEquals("2019-10-15", t.getAt());
    }
    @Test
    public void EventTestII(){
        Task t = new Event("Sample", "2019-10-14", TASK_TYPE.E);
        Assert.assertEquals("Sample", t.getDescription());
        Assert.assertEquals(TASK_TYPE.E, t.getType());
        Assert.assertEquals("2019-10-14", t.getAt());
    }
}
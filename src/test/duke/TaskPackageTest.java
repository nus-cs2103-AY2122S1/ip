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
        Todo t = new Todo("Sample");
        Assert.assertEquals("Sample", t.getDescription());
        Assert.assertEquals(TASK_TYPE.T, t.getType());
    }
    @Test
    public void DeadlineTest(){
        Deadline t = new Deadline("Sample", "2019-10-15");
        Assert.assertEquals("Sample", t.getDescription());
        Assert.assertEquals(TASK_TYPE.D, t.getType());
        Assert.assertEquals(LocalDate.parse("2019-10-15"), t.getBy());
    }
    @Test
    public void EventTest(){
        Event t = new Event("Sample", "2019-10-15");
        Assert.assertEquals("Sample", t.getDescription());
        Assert.assertEquals(TASK_TYPE.E, t.getType());
        Assert.assertEquals("2019-10-15", t.getAt());
    }
}
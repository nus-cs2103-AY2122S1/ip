package duke;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void isEmptyTest(){
        assertTrue(new TaskList().isEmpty());
    }

    @Test
    public void toStringTest(){
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("Test"));
        assertEquals(String.format("1.[T][ ] Test%n"), new TaskList(testList).toString());
    }

    @Test
    public void getSizeTest(){
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("Test1"));
        testList.add(new Todo("Test2"));
        assertEquals(2, new TaskList(testList).getSize());
    }
}

package ligma;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ligma.task.Task;
import ligma.task.Todo;

public class TaskListTest {

    @Test
    public void testGetStringArr() {
        ArrayList<Task> temp = new ArrayList<Task>(
                Arrays.asList(new Todo("shave beard"), new Todo("cry")));
        TaskList taskList = new TaskList(temp);
        String[] expected = new String[]{"[T][ ] shave beard", "[T][ ] cry"};
        String [] actual = taskList.getStringArr();
        assertEquals(2, actual.length);
        for (int i = 0; i < 2; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

}

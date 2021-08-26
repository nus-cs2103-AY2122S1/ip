package ligma;

import ligma.task.Task;
import ligma.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testGetStringArr() {
        ArrayList<Task> temp = new ArrayList<Task>(
                Arrays.asList(new Todo("shave beard"), new Todo("cry")));
        TaskList taskList = new TaskList(temp);
        String[] expected = new String[]{"[T][ ] shave beard", "[T][ ] cry"};
        assertEquals(expected, taskList.getStringArr());
    }

}

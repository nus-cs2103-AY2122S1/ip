import duke.Duke;
import duke.TaskList;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void testTaskListMax() throws NoSuchFieldException, IllegalAccessException {

        Duke duke = new Duke();
        Field taskListField = Duke.class.getDeclaredField("taskList");
        taskListField.setAccessible(true);
        TaskList taskList = (TaskList) taskListField.get(duke);

        Field arrayField = ArrayList.class.getDeclaredField("elementData");
        arrayField.setAccessible(true);
        Object[] internalArray = (Object[])arrayField.get(taskList.getTasks());


        assertEquals(100, internalArray.length);
    }
}

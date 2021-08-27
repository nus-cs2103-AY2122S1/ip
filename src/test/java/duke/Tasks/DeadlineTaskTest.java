package duke.Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void testByWhenString() {
        DeadlineTask dlTask = new DeadlineTask("Homework ", "Tomorrow");

        String byWhenStr = dlTask.getTaskExtraInfo();

        assertEquals("Tomorrow", byWhenStr);
    }

    @Test
    public void testListStringHeader() {
        DeadlineTask dlTask = new DeadlineTask("Homework ", "Tomorrow");

        String headerStr = dlTask.getTaskTypeStringHeader();

        assertEquals("[D]", headerStr);
    }

}

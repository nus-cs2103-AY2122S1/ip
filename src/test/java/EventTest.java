import duke.core.Duke;
import duke.task.Deadline;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void createEventTest(){
        try {
            Duke duke = new Duke("data/todoList2.txt");
            Event event = new Event("test", false, "/at 2020-11-03 4pm");
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }


    }

    @Test
    public void getTimeTest(){
        try {
            Duke duke = new Duke("data/todoList2.txt");
            Event event = new Event("test", false, "/at 2020-11-03 4pm");
            event.getTime();
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }


    }
}
